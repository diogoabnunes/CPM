package pt.up.feup.cpm.printerterminal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

class ShowScanInfo : AppCompatActivity() {
    var transactions_res = ""
    var user_res = ""
    var printed_res=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_scan_info)

        var transactionID= intent.getStringExtra("info").toString()
        val textView = findViewById<TextView>(R.id.transactionId)
        textView.text="Transaction ID: " + transactionID

        runBlocking {
            launch {
                Thread(GetTransaction(this@ShowScanInfo, transactionID)).start()
            }
            delay(3000L)

            val json = JSONObject(transactions_res)
            val transaction = Gson().fromJson(json.toString(), Transaction::class.java)

            if(!transaction.printed){
                transaction.userID?.let { showUserInfo(it) }
                showTransInfo(transaction)
                setPrinted(transaction)
            }
            //else showAlreadyPrinted
        }
    }

    private fun setPrinted(transaction: Transaction?) {
        if (transaction != null) {
            transaction.printed=true
            val body = Gson().toJson(transaction)

            runBlocking {
                launch {
                    Thread(ChangeToPrinted(this@ShowScanInfo,body, transaction.transactionID.toString())).start()
                }
            }
        }

    }


    private fun showTransInfo(transaction: Transaction){
        val total : TextView = findViewById(R.id.total)
        total.text = "Price: "

        val date : TextView = findViewById(R.id.date)
        date.text = "Characteristics: " + transaction.date.toString()

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
}
