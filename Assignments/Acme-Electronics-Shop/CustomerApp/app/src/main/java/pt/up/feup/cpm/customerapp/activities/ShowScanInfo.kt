package pt.up.feup.cpm.customerapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import pt.up.feup.cpm.customerapp.R
import pt.up.feup.cpm.customerapp.models.Product
import pt.up.feup.cpm.customerapp.utils.GetProducts

class ShowScanInfo : AppCompatActivity() {
    private var products : ArrayList<Product>? = null
    private val result by lazy { findViewById<TextView>(R.id.result) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_scan_info)

        var productID = intent.getStringExtra("info")
        val textView : TextView = findViewById(R.id.tv_show_info)
        textView.text=productID

        getDBProducts()

        for (product in products!!) {
            if (productID == product.getProductID()) {
                showProduct(product)
            }
            else {
                textView.text = "There is no product with id " + productID
            }
        }
    }

    private fun getDBProducts() {
        Thread(
            GetProducts(this)
        ).start()
    }

    private fun showProduct(product: Product) {
        // TO DO: how to show in app
    }

    fun appendText(value: String) {
        runOnUiThread { result.text = result.text.toString() + "\n" + value }
    }

    fun writeText(value: String) {
        runOnUiThread { result.text = value }
    }
}