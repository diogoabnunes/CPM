package pt.up.feup.cpm.customerapp.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import org.json.JSONArray
import pt.up.feup.cpm.customerapp.R
import pt.up.feup.cpm.customerapp.models.Product
import pt.up.feup.cpm.customerapp.models.Transaction
import pt.up.feup.cpm.customerapp.utils.GetProduct

class ShowScanInfo : AppCompatActivity() {
    val products_res by lazy { findViewById<TextView>(R.id.products_res) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_scan_info)

        val productID = intent.getStringExtra("info")
        val textView : TextView = findViewById(R.id.tv_show_info)
        textView.text=productID

        Thread(GetProduct(this, productID)).start()

        val transaction = intent.getSerializableExtra("value") as Transaction

        transaction.addItem(productID!!, 1)

        val linkTextView2 = findViewById<TextView>(R.id.btn_back_shopping_cart)
        linkTextView2.setOnClickListener {
            val data = Intent()
            data.putExtra("value", transaction)
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }

    fun writeText(value: String) {
        runOnUiThread { products_res.text = value }
    }
}