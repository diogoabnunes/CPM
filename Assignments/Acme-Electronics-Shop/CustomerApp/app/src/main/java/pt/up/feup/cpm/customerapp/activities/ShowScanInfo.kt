package pt.up.feup.cpm.customerapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import pt.up.feup.cpm.customerapp.R
import pt.up.feup.cpm.customerapp.models.Product

class ShowScanInfo : AppCompatActivity() {
    private var products : List<Product>? = Home::getHomeProducts.call()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_scan_info)

        var productID = intent.getStringExtra("info")
        val textView : TextView = findViewById(R.id.tv_show_info)
        textView.text=productID

        for (product in products!!) {
            if (productID == product.getProductID()) {
                showProduct(product)
            }
            else {
                textView.text = "There is no product with id: $productID"
            }
        }
    }

    private fun showProduct(product: Product) {
        // Info about the product
        // Button to add to shopping cart
    }
}