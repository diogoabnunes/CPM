package pt.up.feup.cpm.customerapp.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import org.json.JSONObject
import pt.up.feup.cpm.customerapp.R
import pt.up.feup.cpm.customerapp.models.Product
import pt.up.feup.cpm.customerapp.http.GetProduct
import java.util.ArrayList

class ShowScanInfo : AppCompatActivity() {
    var products_res = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_scan_info)

        val productID = intent.getStringExtra("info")
        val textView : TextView = findViewById(R.id.tv_show_info)
        textView.text = "productID: " + productID

        Thread(GetProduct(this, productID)).start()

        val products = intent.getSerializableExtra("value2") as MutableList<Product>
        val quantities = intent.getSerializableExtra("quants2") as MutableList<Int>

        val json = JSONObject(products_res)
        val prod = Gson().fromJson(json.toString(), Product::class.java)
        products.add(prod)
        quantities.add(1)

        val name : TextView = findViewById(R.id.tv_show_info2)
        name.text = "Name: " + prod.name

        val make : TextView = findViewById(R.id.tv_show_info3)
        make.text = "Make: " +prod.make

        val model : TextView = findViewById(R.id.tv_show_info4)
        model.text = "Model: " +prod.model

        val price : TextView = findViewById(R.id.tv_show_info5)
        price.text = "Price: " +prod.price.toString()

        val charac : TextView = findViewById(R.id.tv_show_info6)
        charac.text = "Characteristics: " +prod.characteristics




        val linkTextView2 = findViewById<TextView>(R.id.btn_back_shopping_cart)
        linkTextView2.setOnClickListener {
            val data = Intent()
            data.putExtra("value3", ArrayList(products))
            data.putExtra("quants3", ArrayList(quantities))
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }
}