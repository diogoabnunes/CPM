package pt.up.feup.cpm.customerapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import org.json.JSONObject
import pt.up.feup.cpm.customerapp.R
import pt.up.feup.cpm.customerapp.http.GetCustomer
import pt.up.feup.cpm.customerapp.models.Customer

class Home : AppCompatActivity() {
    var customer = Customer()
    var user_response = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupShoppingCart()
        setupPastTransitions()

        val email = intent.getStringExtra("email").toString()

        Thread(GetCustomer(this, email)).start()

        val json = JSONObject(user_response)
        customer = Gson().fromJson(json.toString(), Customer::class.java)
        val b = 1
    }

    private fun setupShoppingCart() {
        val shoppingCartBtn = findViewById<Button>(R.id.shopping_cart_button)
        shoppingCartBtn.setOnClickListener {
            val intent = Intent(this, ShoppingCart::class.java)
            intent.putExtra("customer", customer)
            startActivity(intent)
        }
    }

    private fun setupPastTransitions() {
        val pastTransitionsBtn = findViewById<Button>(R.id.past_transactions_button)
        pastTransitionsBtn.setOnClickListener {
            val intent = Intent(this, PastTransactions::class.java)
            intent.putExtra("customer", customer)
            startActivity(intent)
        }
    }
}