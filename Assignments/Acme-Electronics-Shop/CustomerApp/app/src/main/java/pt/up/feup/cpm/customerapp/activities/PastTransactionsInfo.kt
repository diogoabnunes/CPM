package pt.up.feup.cpm.customerapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import pt.up.feup.cpm.customerapp.R
import pt.up.feup.cpm.customerapp.models.Transaction

class PastTransactionsInfo : AppCompatActivity() {
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past_transactions_info)



        val transactionId = findViewById<TextView>(R.id.transaction_id)
        val date = findViewById<TextView>(R.id.date)
        val total = findViewById<TextView>(R.id.total)

    }
    private lateinit var webView: TextView
    companion object {
        fun newIntent(context: Context, transaction: Transaction): Intent {
        val detailIntent = Intent(context, PastTransactionsInfo::class.java)

        detailIntent.putExtra("date", transaction.date)

        return detailIntent
    }}

}