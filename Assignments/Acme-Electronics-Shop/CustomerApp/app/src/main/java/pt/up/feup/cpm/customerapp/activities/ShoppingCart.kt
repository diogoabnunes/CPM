package pt.up.feup.cpm.customerapp.activities

import android.app.Activity
import android.content.Intent
import android.database.DataSetObserver
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import pt.up.feup.cpm.customerapp.R
import pt.up.feup.cpm.customerapp.adapter.ShoppingCartAdapter
import pt.up.feup.cpm.customerapp.http.AddTransaction
import pt.up.feup.cpm.customerapp.models.Product
import pt.up.feup.cpm.customerapp.models.Transaction
import pt.up.feup.cpm.customerapp.models.TransactionItem
import pt.up.feup.cpm.customerapp.models.Customer
import kotlin.collections.mutableListOf

class ShoppingCart : AppCompatActivity() {
    private var products = mutableListOf<Product>()
    var totalText: TextView? = null
    val transaction_res by lazy { findViewById<TextView>(R.id.transactions_res) }
    var list = mutableListOf<TransactionItem>()
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
        totalText = findViewById(R.id.total)

        val transactionName = arrayOf( "iPad","Smartphone","Watch","Mouse","HeadPhones","Computer", "KeyBoard", "Door")
        val quantity = arrayOf(2,1,1,1,3,4,2,6)
        val price = arrayOf(10.5, 3.8, 2.0, 177.99, 35.4, 8.6, 23.0, 16.8, 234.6)

        for (i in transactionName.indices){
            list.add(
                TransactionItem(Product("1", transactionName[i], price[i], "b", "c", "d"), quantity[i])
            )
        }
        listview.adapter = ShoppingCartAdapter(this, R.layout.list_item, list)
        listview.adapter.registerDataSetObserver(observer);
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
        val random = (0..100).shuffled().random()
        if(random <= 95) {
            Toast.makeText(this, "Payment Success! $random", Toast.LENGTH_SHORT).show()

            val transaction = Transaction()
            saveTransaction(transaction)
            generateQR(vw, transaction)
        }
        else
            Toast.makeText(this, "Payment Failed! $random", Toast.LENGTH_SHORT).show()
    }

    private fun saveTransaction(transaction: Transaction) {
        val customer = intent.getSerializableExtra("customer") as Customer
        transaction.userID = customer.userID
        val content = mutableListOf<TransactionItem>()
        for (i in products) {
            val transactionItem = TransactionItem(i, 1)
            content.add(transactionItem)
        }
        transaction.content = content
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

    fun calculateTotal(): Double {
        var total = 0.0
        for (product in list) {
            var price = product.product?.price
            var quanti = product.quantity
            var num = price?.times(quanti!!)
            if (num != null) {
                total += num
            }
        }
        return String.format("%.2f", total).toDouble()
    }
    var observer: DataSetObserver = object : DataSetObserver() {
        override fun onChanged() {
            super.onChanged()
            setTotal()
        }
    }

    fun setTotal(){
        totalText?.setText("Total: " + calculateTotal() + "€")
    }

}
