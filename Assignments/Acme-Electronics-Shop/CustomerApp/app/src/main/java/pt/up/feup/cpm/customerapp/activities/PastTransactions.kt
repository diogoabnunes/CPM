package pt.up.feup.cpm.customerapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import pt.up.feup.cpm.customerapp.R
import pt.up.feup.cpm.customerapp.adapter.TransactionItemAdapter
import pt.up.feup.cpm.customerapp.models.Transaction
import java.util.*


class PastTransactions : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past_transactions)

        var listview = findViewById<ListView>(R.id.list_item)
        var list = mutableListOf<Transaction>()

        val transactionId = arrayOf( "12","13","15","16","17","18")
        //val date = arrayOf(Date("2018-12-31"), Date("2018-12-31"), Date("2018-12-31"), Date("2018-12-31"), Date("2018-12-31"), Date("2018-12-31"))
        val price = arrayOf("10$00", "14$00", "99$00", "108$99", "78$45", "345$98")


        for (i in transactionId.indices){
            list.add(Transaction(transactionId[i], "29", null, "22/03/2022", price[i] )
            )
        }

        listview.adapter = TransactionItemAdapter(this, R.layout.past_transaction_item, list)

    }
}