package pt.up.feup.cpm.customerapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import pt.up.feup.cpm.customerapp.R
import pt.up.feup.cpm.customerapp.adapter.TransactionItemAdapter
import pt.up.feup.cpm.customerapp.http.GetTransactions
import pt.up.feup.cpm.customerapp.models.Customer
import pt.up.feup.cpm.customerapp.models.Product
import pt.up.feup.cpm.customerapp.models.Transaction
import pt.up.feup.cpm.customerapp.models.TransactionItem
import java.util.*

class PastTransactions : AppCompatActivity() {
    var response = ""
    var transactions_res = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past_transactions)

        val customer = intent.getSerializableExtra("customer") as Customer

        runBlocking {
            launch {
                Thread(customer.userID?.let { GetTransactions(this@PastTransactions, it) })
            }
            delay(1000L)


            //val json = JSONObject(transactions_res)
            //val transactions= Gson().fromJson(json.toString(), Transaction::class.java)
            val lista = mutableListOf<Transaction>()
            val listaa = mutableListOf<TransactionItem>()

            listaa.add(TransactionItem(Product("222","produv", 33.5, "nhe", "nhu","njndj"), 3))
            listaa.add(TransactionItem(Product("223","produv", 353.5, "nhe", "nhu","njndj"), 6))
            listaa.add(TransactionItem(Product("224","produv", 33.5, "nhe", "nhu","njndj"), 3))

            lista.add(Transaction("2223", "userid", listaa, "22/02/2022"))
            lista.add(Transaction("2224", "userid", listaa, "22/02/2022"))
            lista.add(Transaction("2225", "userid", listaa, "26/03/2028"))
            val listview = findViewById<ListView>(R.id.list_item)

            listview.adapter = TransactionItemAdapter(this@PastTransactions, R.layout.past_transaction_item, lista)

            val context = this@PastTransactions
            listview.setOnItemClickListener { _, _, position, _ ->
                val selectedRecipe = lista[position]
                val detailIntent = PastTransactionsInfo.newIntent(context, selectedRecipe)
                detailIntent.putExtra("id", lista[position].transactionID);
                detailIntent.putExtra("data", lista[position].date);
                startActivity(detailIntent)
            }
        }
    }
}