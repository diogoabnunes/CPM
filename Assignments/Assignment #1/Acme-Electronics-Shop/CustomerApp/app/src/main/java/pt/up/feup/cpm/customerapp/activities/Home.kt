package pt.up.feup.cpm.customerapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.coroutines.*
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
        setupLogout()

        val email = intent.getStringExtra("email").toString()

        try {
            runBlocking {
                launch {
                    Thread(GetCustomer(this@Home, email)).start()
                }
                delay(1000L)
                var json = JSONObject()
                launch {
                    json = JSONObject(user_response)
                }
                delay(1000L)
                customer = Gson().fromJson(json.toString(), Customer::class.java)
            }
        }
        catch (e: Exception) {
            Toast.makeText(this@Home, "Failed to connect to Database...", Toast.LENGTH_LONG).show()
        }
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

    private fun setupLogout() {
        val logoutBtn = findViewById<Button>(R.id.logout_button)
        logoutBtn.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}