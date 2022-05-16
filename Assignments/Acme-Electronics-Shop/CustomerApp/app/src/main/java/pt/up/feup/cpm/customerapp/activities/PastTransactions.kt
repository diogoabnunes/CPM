package pt.up.feup.cpm.customerapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import pt.up.feup.cpm.customerapp.R
import pt.up.feup.cpm.customerapp.adapter.TransactionItemAdapter
import pt.up.feup.cpm.customerapp.http.GetTransactions
import pt.up.feup.cpm.customerapp.models.Customer
import pt.up.feup.cpm.customerapp.models.Transaction
import java.util.*

class PastTransactions : AppCompatActivity() {
    var pastTransactions = mutableListOf<Transaction>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past_transactions)

        val customer = intent.getSerializableExtra("customer") as Customer

        runBlocking {
            launch {
                Thread(customer.userID?.let { GetTransactions(this@PastTransactions, it) })
            }
            delay(1000L)
            
            val listview = findViewById<ListView>(R.id.list_item)
            val list = mutableListOf<Transaction>()

            val transactionId = arrayOf( "12","13","15","16","17","18")
            //val date = arrayOf(Date("2018-12-31"), Date("2018-12-31"), Date("2018-12-31"), Date("2018-12-31"), Date("2018-12-31"), Date("2018-12-31"))
            val price = arrayOf("10$00", "14$00", "99$00", "108$99", "78$45", "345$98")


//        for (i in transactionId.indices){
//            list.add(Transaction(transactionId[i], "29", null, "22/03/2022", price[i] )
//            )
//        }

//        for (i in pastTransactions.indices) {
//            list.add(Transaction(pastTransactions[i].transactionID, pastTransactions[i].userID,
//                pastTransactions[i].content, pastTransactions[i].date, pastTransactions[i].printed))
//        }

            listview.adapter = TransactionItemAdapter(this@PastTransactions, R.layout.past_transaction_item, list)

            val context = this@PastTransactions
            listview.setOnItemClickListener { _, _, position, _ ->
                // 1
                val selectedRecipe = list[position]

                // 2
                val detailIntent = PastTransactionsInfo.newIntent(context, selectedRecipe)

                // 3
                startActivity(detailIntent)
            }
        }
    }
}