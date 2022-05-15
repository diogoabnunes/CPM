package pt.up.feup.cpm.customerapp.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.gson.Gson
import com.google.zxing.integration.android.IntentIntegrator
import org.json.JSONObject
import pt.up.feup.cpm.customerapp.R
import pt.up.feup.cpm.customerapp.adapter.ShoppingCartAdapter
import pt.up.feup.cpm.customerapp.adapter.TransactionItemAdapter
import pt.up.feup.cpm.customerapp.models.Customer
import pt.up.feup.cpm.customerapp.models.Product
import pt.up.feup.cpm.customerapp.models.Transaction
import pt.up.feup.cpm.customerapp.models.TransactionItem
import pt.up.feup.cpm.customerapp.utils.AddTransaction
import kotlin.collections.mutableListOf

class ShoppingCart : AppCompatActivity() {
    private var products = mutableListOf<Product>()

    val transaction_res by lazy { findViewById<TextView>(R.id.transactions_res) }

    val getAddItem = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            products = result.data?.getSerializableExtra("value4") as MutableList<Product>
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)

        setupPayButton()
        setupAddItemButton()

        var listview = findViewById<ListView>(R.id.list_item)
        var list = mutableListOf<TransactionItem>()

        val transactionName = arrayOf( "iPad","Smartphone","Watch","Mouse","HeadPhones","Computer", "KeyBoard", "Door")
        val quantity = arrayOf(2,1,1,1,3,4,2,6)
        val price = arrayOf("10$00", "14$00", "99$00", "108$99", "78$45", "345$98", "30$33", "4$00", "2$00" )

        for (i in transactionName.indices){
            list.add(
                TransactionItem(Product("1", transactionName[i], 2.3, "b", "c", "d"), 1)
            )
        }

        listview.adapter = ShoppingCartAdapter(this, R.layout.list_item, list)
    }

    private fun setupPayButton() {
        findViewById<Button>(R.id.pay_btn).setOnClickListener {
                vw -> onButtonClick(vw) }
    }

    private fun setupAddItemButton() {
        val linkTextView2 = findViewById<TextView>(R.id.add_item_btn)
        linkTextView2.setOnClickListener {

            val intent = Intent(this, Scan::class.java)
            intent.putExtra("value1", ArrayList(products))
            getAddItem.launch(intent)
        }
    }

    private fun onButtonClick(vw: View) {
        var random =(0..100).shuffled().random()
        if(random<=95){
            Toast.makeText(this, "Payment Success! $random", Toast.LENGTH_SHORT).show()

            var transaction = Transaction()
            saveTransaction(transaction)
            generateQR(vw, transaction)
        }
        else
            Toast.makeText(this, "Payment Failed! $random", Toast.LENGTH_SHORT).show()
    }

    private fun saveTransaction(transaction: Transaction) {
        transaction.setUserID("a0c8891a-5aeb-4e9e-974c-cdc9ba14cb0f")
        val content = mutableListOf<TransactionItem>()
        for (i in products) {
            val transactionItem = TransactionItem(i, 1)
            content.add(transactionItem)
        }
        transaction.setContent(content)
        val js: String? = Gson().toJson(transaction)
        println(js)
        Thread(AddTransaction(this, js.toString())).start()
    }

    private fun generateQR(vw: View, transaction: Transaction) {

        val intent = Intent(this, ShowQR::class.java)
        when (vw.id) {
            R.id.pay_btn -> { intent.putExtra("type", 1)
                val value=Gson().toJson(transaction)
                intent.putExtra("value", value)
            }
        }
        startActivity(intent)
    }

    fun writeText(value: String) {
        runOnUiThread { transaction_res.text = value }
    }
}
