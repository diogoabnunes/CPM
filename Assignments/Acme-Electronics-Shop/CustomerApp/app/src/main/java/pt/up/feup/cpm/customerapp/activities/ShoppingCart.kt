package pt.up.feup.cpm.customerapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import pt.up.feup.cpm.customerapp.R
import pt.up.feup.cpm.customerapp.models.Product
import pt.up.feup.cpm.customerapp.utils.GetProducts
import java.util.ArrayList

class ShoppingCart : AppCompatActivity() {
    var cartContent: ArrayList<Pair<Product, Int>>? = null
    var httpResponse : String? = null

    private val payButton by lazy { findViewById<Button>(R.id.pay_btn) }
    private val addItemButton by lazy { findViewById<TextView>(R.id.add_item_btn) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)

        setupPay()
        setupAddItem()
    }

    private fun setupPay() {
        payButton.setOnClickListener { view -> onButtonClick(view) }
    }

    private fun setupAddItem() {
        addItemButton.setOnClickListener {
            val intent = Intent(this, Scan::class.java)
            startActivity(intent)
        }
    }

    private fun onButtonClick(vw: View) {

        // to delete
        val title = "Hello"
        val author = "World"
        val categories: List<String> = listOf("123","me llamo jeff")

        val intent = Intent(this, ShowQR::class.java)
        when (vw.id) {
            R.id.pay_btn -> { intent.putExtra("type", 1)
                val value="Category [title: ${title}, author: ${author}, categories: ${categories}]"
                intent.putExtra("value", value)
            }
        }
        startActivity(intent)
        // to delete

        // Operation 3: the device contacts the shop service, sends his user ID, basket content,
        // signed with the private key stored when the registration was performed (SHA256WithRSA)

        // get info from activity from each product and quantity to create transaction
        // Thread(AddTransaction(transaction)).start()
    }
}