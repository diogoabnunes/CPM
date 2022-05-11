package pt.up.feup.cpm.customerapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import pt.up.feup.cpm.customerapp.R

class ShoppingCart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)

        findViewById<Button>(R.id.pay_btn).setOnClickListener { vw -> onButtonClick(vw) }
        addItemHandler()

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