package pt.up.feup.cpm.customerapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import pt.up.feup.cpm.customerapp.R
import pt.up.feup.cpm.customerapp.adapter.ShoppingCartAdapter
import pt.up.feup.cpm.customerapp.adapter.TransactionItemAdapter
import pt.up.feup.cpm.customerapp.models.Transaction
import pt.up.feup.cpm.customerapp.models.TransactionItem


class ShoppingCart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)

        findViewById<Button>(R.id.pay_btn).setOnClickListener { vw -> onButtonClick(vw) }
        addItemHandler()

        var listview = findViewById<ListView>(R.id.list_item)
        var list = mutableListOf<TransactionItem>()

        val transactionName = arrayOf( "iPad","Smartphone","Watch","Mouse","HeadPhones","Computer", "KeyBoard", "Door")
        val quantity = arrayOf(2,1,1,1,3,4,2,6)
        val price = arrayOf("10$00", "14$00", "99$00", "108$99", "78$45", "345$98", "30$33", "4$00", "2$00" )


        for (i in transactionName.indices){
            list.add(
                TransactionItem("12", transactionName[i], quantity[i], price[i] )
            )
        }

        listview.adapter = ShoppingCartAdapter(this, R.layout.list_item, list)

    }


    private fun addItemHandler(){
        val linkTextView2 = findViewById<TextView>(R.id.add_item_btn)
        linkTextView2.setOnClickListener {
            val intent = Intent(this, Scan::class.java)
            startActivity(intent)
        }
    }

    private fun onButtonClick(vw: View) {
        val title: String
        val author: String
        val categories: List<String>

        title="Hello"
        author="World"
        categories= listOf("123","me llamo jeff")


        val intent = Intent(this, ShowQR::class.java)
        when (vw.id) {
            R.id.pay_btn -> { intent.putExtra("type", 1)
                val value="Category [title: ${title}, author: ${author}, categories: ${categories}]"
                intent.putExtra("value", value)
            }
        }
        startActivity(intent)

    }
}