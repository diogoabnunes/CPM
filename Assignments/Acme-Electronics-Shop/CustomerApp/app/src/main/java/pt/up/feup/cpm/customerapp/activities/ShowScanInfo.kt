package pt.up.feup.cpm.customerapp.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import pt.up.feup.cpm.customerapp.R
import pt.up.feup.cpm.customerapp.models.Product
import pt.up.feup.cpm.customerapp.http.GetProduct
import java.util.ArrayList

class ShowScanInfo : AppCompatActivity() {
    val products_res by lazy { findViewById<TextView>(R.id.products_res) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_scan_info)

        val productID = intent.getStringExtra("info")
        val textView : TextView = findViewById(R.id.tv_show_info)
        textView.text = productID

        Thread(GetProduct(this, productID)).start()

        val products = intent.getSerializableExtra("value2") as MutableList<Product>

        val str = Product("1", "a", 2.3, "b", "c", "d")

        products.add(str)

        val linkTextView2 = findViewById<TextView>(R.id.btn_back_shopping_cart)
        linkTextView2.setOnClickListener {
            val data = Intent()
            data.putExtra("value3", ArrayList(products))
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }

    fun writeText(value: String) {
        runOnUiThread { products_res.text = value }
    }
}