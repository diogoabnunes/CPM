package pt.up.feup.cpm.customerapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import pt.up.feup.cpm.customerapp.R
import pt.up.feup.cpm.customerapp.adapter.ShoppingCartAdapter
import pt.up.feup.cpm.customerapp.adapter.TransactionItemAdapter
import pt.up.feup.cpm.customerapp.adapter.TransactionItemAdapter2
import pt.up.feup.cpm.customerapp.http.GetTransaction
import pt.up.feup.cpm.customerapp.models.Transaction
import pt.up.feup.cpm.customerapp.models.TransactionItem

class PastTransactionsInfo : AppCompatActivity() {
    var transactions_res = ""
    val listview by lazy { findViewById<ListView>(R.id.list_item) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past_transactions_info)

        var ss:String = intent.getStringExtra("id").toString()
        var sss:String = intent.getStringExtra("data").toString()
        runBlocking {
            launch {
                Thread(GetTransaction(this@PastTransactionsInfo, ss)).start()
            }
            delay(1000L)


            val json = JSONObject(transactions_res)
            val transaction = Gson().fromJson(json.toString(), Transaction::class.java)
            val transactionId = findViewById<TextView>(R.id.transaction_id)
            val date = findViewById<TextView>(R.id.date)
            val total : TextView = findViewById(R.id.total)

            total.text = "Total: " + calculateTotal(transaction.content)
            transactionId.text = "#" + ss.take(4).uppercase() + ss.takeLast(4).uppercase()
            date.text = sss
            listview.adapter = TransactionItemAdapter2(this@PastTransactionsInfo, R.layout.list_item, transaction.content)
        }


    }
    companion object {
        fun newIntent(context: Context, transaction: Transaction): Intent {
        val detailIntent = Intent(context, PastTransactionsInfo::class.java)
        return detailIntent
    }}
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