package pt.up.feup.cpm.customerapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import pt.up.feup.cpm.customerapp.R

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupShoppingCart()
        setupPastTransitions()
    }

    private fun setupShoppingCart() {
        val shoppingCartBtn = findViewById<Button>(R.id.shopping_cart_button)
        shoppingCartBtn.setOnClickListener {
            val intent = Intent(this, ShoppingCart::class.java)
            startActivity(intent)
        }
    }

    private fun setupPastTransitions() {
        val pastTransitionsBtn = findViewById<Button>(R.id.past_transactions_button)
        pastTransitionsBtn.setOnClickListener {
            val intent = Intent(this, PastTransactions::class.java)
            startActivity(intent)
        }
    }
}