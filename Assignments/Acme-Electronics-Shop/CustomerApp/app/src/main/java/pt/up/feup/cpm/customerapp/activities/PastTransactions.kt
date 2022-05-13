package pt.up.feup.cpm.customerapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pt.up.feup.cpm.customerapp.R
import pt.up.feup.cpm.customerapp.models.Transaction
import java.util.ArrayList

class PastTransactions : AppCompatActivity() {
    var pastTransactions : ArrayList<Transaction>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past_transactions)

        //getPastTransactions()


    }
}