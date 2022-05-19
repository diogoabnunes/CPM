package pt.up.feup.cpm.customerapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.gson.GsonBuilder
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import pt.up.feup.cpm.customerapp.R
import pt.up.feup.cpm.customerapp.adapter.TransactionItemAdapter
import pt.up.feup.cpm.customerapp.http.GetTransactions
import pt.up.feup.cpm.customerapp.models.Customer
import pt.up.feup.cpm.customerapp.models.Transaction
import pt.up.feup.cpm.customerapp.models.TransactionItem
import java.util.*

class PastTransactions : AppCompatActivity() {
    var pastTransactions: List<Transaction> = ArrayList()
    var response = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past_transactions)

        val customer = intent.getSerializableExtra("customer") as Customer
        val a = 1

        runBlocking {
            launch {
                Thread(customer.userID?.let { GetTransactions(this@PastTransactions, it) }).start()
            }
            delay(1000L)

            pastTransactions =
                GsonBuilder().create().fromJson(response, Array<Transaction>::class.java).toList()

            val listview = findViewById<ListView>(R.id.list_item)
            val list = mutableListOf<Transaction>()

            for (i in pastTransactions.indices) {
                list.add(
                    Transaction(
                        pastTransactions[i].transactionID,
                        pastTransactions[i].userID,
                        pastTransactions[i].content,
                        pastTransactions[i].date,
                        pastTransactions[i].printed
                    )
                )


                listview.adapter = TransactionItemAdapter(
                    this@PastTransactions,
                    R.layout.past_transaction_item,
                    list
                )

                val context = this@PastTransactions
                listview.setOnItemClickListener { _, _, position, _ ->
                    val selectedRecipe = list[position]
                    val detailIntent = PastTransactionsInfo.newIntent(context, selectedRecipe)
                    detailIntent.putExtra("id", list[position].transactionID);
                    detailIntent.putExtra("data", list[position].date);
                    detailIntent.putExtra("price", calculateTotal(list[position].content))
                    startActivity(detailIntent)
                }
            }
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