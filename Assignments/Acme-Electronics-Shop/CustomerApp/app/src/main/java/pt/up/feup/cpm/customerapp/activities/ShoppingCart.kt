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
    private var quantities = mutableListOf<Int>()

    val totalText by lazy { findViewById<TextView>(R.id.total) }
    var transaction_res = ""
    var list = mutableListOf<TransactionItem>()
    val listview by lazy { findViewById<ListView>(R.id.list_item) }

    val getAddItem = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            products = result.data?.getSerializableExtra("value4") as MutableList<Product>
            quantities = result.data?.getSerializableExtra("quants4") as MutableList<Int>

            list.clear()
            for (i in products.indices){
                list.add(
                    TransactionItem(products[i], quantities[i])
                )
            }
            listview.adapter = ShoppingCartAdapter(this, R.layout.list_item, list)
            listview.adapter.registerDataSetObserver(observer);
            setTotal()
        }
    }

    var observer: DataSetObserver = object : DataSetObserver() {
        override fun onChanged() {
            super.onChanged()

            quantities.clear()
            for (i in list) {
                i.quantity?.let { quantities.add(it) }
            }
            setTotal()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)

        setupPayButton()
        setupAddItemButton()

        for (i in products.indices){
            list.add(
                TransactionItem(products[i], quantities[i])
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
            intent.putExtra("quants1", ArrayList(quantities))
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
        for (i in products.indices) {
            val transactionItem = TransactionItem(products[i], quantities[i])
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

    private fun calculateTotal(): String {
        var total = 0.0
        for (transactionItem in list) {
            val price = transactionItem.product?.price
            val quanti = transactionItem.quantity
            val num = price?.times(quanti!!)
            if (num != null) {
                total += num
            }
        }
        return "%.2f".format(total)
    }

    fun setTotal(){
        val calcTotal = "Total: " + calculateTotal() + "€"
        totalText?.text = calcTotal
    }
}
