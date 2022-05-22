package pt.up.feup.cpm.customerapp.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
        val textView: TextView = findViewById(R.id.tv_show_info)
        textView.text = "Product ID: " + productID

        val products = intent.getSerializableExtra("value2") as MutableList<Product>
        val quantities = intent.getSerializableExtra("quants2") as MutableList<Int>

        try {
            runBlocking {
                launch {
                    Thread(GetProduct(this@ShowScanInfo, productID)).start()
                }
                delay(1000L)

                val json = JSONObject(products_res)
                val prod = Gson().fromJson(json.toString(), Product::class.java)
                showInfo(prod)

                val linkTextView2 = findViewById<TextView>(R.id.btn_back_shopping_cart)
                linkTextView2.setOnClickListener {
                    products.add(prod)
                    quantities.add(1)

                    val data = Intent()
                    data.putExtra("value3", ArrayList(products))
                    data.putExtra("quants3", ArrayList(quantities))
                    setResult(Activity.RESULT_OK, data)
                    finish()
                }

                val cancelBtn = findViewById<Button>(R.id.cancel_btn)
                cancelBtn.setOnClickListener {
                    val data = Intent()
                    data.putExtra("value3", ArrayList(products))
                    data.putExtra("quants3", ArrayList(quantities))
                    setResult(Activity.RESULT_OK, data)
                    finish()
                }
            }
        }
        catch (e: Exception) {
            Toast.makeText(this@ShowScanInfo, "Failed to connect to Database..." ,Toast.LENGTH_LONG).show()
            val data = Intent()
            data.putExtra("value3", ArrayList(products))
            data.putExtra("quants3", ArrayList(quantities))
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }



    private fun showInfo(prod:Product){
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
    }
}