package pt.up.feup.cpm.customerapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pt.up.feup.cpm.customerapp.R
import pt.up.feup.cpm.customerapp.models.Product
import pt.up.feup.cpm.customerapp.models.Transaction
import pt.up.feup.cpm.customerapp.utils.GetProducts
import pt.up.feup.cpm.customerapp.utils.GetTransactions
import java.util.ArrayList

class Home : AppCompatActivity() {
    var products : List<Product>? = ArrayList<Product>()
    var transactions : List<Transaction>? = ArrayList<Transaction>()
    var httpResponse = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getProductsFromDB()
        getTransactionsFromDB()

        setupShoppingCart()
        setupPastTransitions()
    }

    private fun getProductsFromDB() {
        Thread(GetProducts(this)).start()

        val typeToken = object : TypeToken<List<Product>>() {}.type
        products = Gson().fromJson<List<Product>>(httpResponse, typeToken)
    }

    private fun getTransactionsFromDB() {
        Thread(GetTransactions(this)).start()

        val typeToken = object : TypeToken<List<Transaction>>() {}.type
        transactions = Gson().fromJson<List<Transaction>>(httpResponse, typeToken)
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

    fun appendText(value: String) {
        runOnUiThread { httpResponse += "\n" + value }
    }

    fun writeText(value: String) {
        runOnUiThread { httpResponse = value }
    }

    fun getHomeProducts() : List<Product>? {
        return this.products
    }

    fun getHomeTransactions() : List<Transaction>? {
        return this.transactions
    }
}