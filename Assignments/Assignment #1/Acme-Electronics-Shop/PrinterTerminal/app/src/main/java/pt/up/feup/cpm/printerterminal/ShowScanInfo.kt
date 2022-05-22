package pt.up.feup.cpm.printerterminal

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import com.google.gson.Gson
import org.json.JSONObject
import pt.up.feup.cpm.printerterminal.http.ChangeToPrinted
import pt.up.feup.cpm.printerterminal.models.Customer
import pt.up.feup.cpm.printerterminal.models.Transaction
import pt.up.feup.cpm.printerterminal.http.GetCustomer
import pt.up.feup.cpm.printerterminal.http.GetTransaction
import pt.up.feup.cpm.printerterminal.models.TransactionItem

class ShowScanInfo : AppCompatActivity() {
    var transactions_res = ""
    var user_res = ""
    var printed_res=""
    val tv by lazy { findViewById<TextView>(R.id.textView6) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_scan_info)

        var transactionID= intent.getStringExtra("info").toString()

        runBlocking {
            launch {
                Thread(GetTransaction(this@ShowScanInfo, transactionID)).start()
            }
            delay(1000L)

            try {
                val json = JSONObject(transactions_res)
                val transaction = Gson().fromJson(json.toString(), Transaction::class.java)

                if(!transaction.printed){
                    setPrinted(transaction)
                    transaction.userID?.let { showUserInfo(it) }
                    showTransInfo(transaction)
                    var list = transaction.content
                    val listview by lazy { findViewById<ListView>(R.id.list_item) }
                    listview.adapter = InfoAdapter(this@ShowScanInfo, R.layout.list_item_transaction, list)


                }
                else {
                    tv.text = "Already printed"
                }
            }
            catch (e:Exception) {
                tv.text = "Failed to connect to Database..."
            }

        }
    }

    private fun setPrinted(transaction: Transaction?) {
        if (transaction != null) {
            runBlocking {
                launch {
                    Thread(ChangeToPrinted(this@ShowScanInfo,transaction.transactionID.toString())).start()
                }
                delay(1000L)
            }
        }

    }


    private fun showTransInfo(transaction: Transaction){
        val textView = findViewById<TextView>(R.id.transactionId)
        textView.text="Transaction ID: " + transaction.transactionID

        val total : TextView = findViewById(R.id.total)
        total.text = "Total: " + calculateTotal(transaction.content)

        val date : TextView = findViewById(R.id.date)
        val hora = transaction.date.toString().takeLast(13)
        date.text = "Date: " + transaction.date.toString().take(10) + "     " + hora.take(8)

    }
    private fun showUserInfo(userID: String){
        runBlocking {
            launch {
                Thread(GetCustomer(this@ShowScanInfo, userID)).start()
            }
            delay(1000L)
            val json = JSONObject(user_res)
            val user = Gson().fromJson(json.toString(), Customer::class.java)

            val name : TextView = findViewById(R.id.name)
            name.text = "Name: " + user.name

            val address : TextView = findViewById(R.id.address)
            address.text = "Address: "+ user.address

            val nib : TextView = findViewById(R.id.nib)
            nib.text = "Fiscal Number: "+ user.fiscalNumber

        }
    }

    private fun calculateTotal(transactionItems: MutableList<TransactionItem>): String {
        var total = 0.0
        for (transactionItem in transactionItems ) {
            val price = transactionItem.product?.price
            val quanti = transactionItem.quantity
            val num = price?.times(quanti!!)
            if (num != null) {
                total += num
            }
        }
        return "%.2f".format(total)
    }
}
