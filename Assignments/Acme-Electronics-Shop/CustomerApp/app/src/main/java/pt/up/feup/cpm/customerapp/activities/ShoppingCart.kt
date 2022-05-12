package pt.up.feup.cpm.customerapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import pt.up.feup.cpm.customerapp.R

class ShoppingCart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)

        var productName = findViewById<TextView>(R.id.productName)
        var productPrice = findViewById<TextView>(R.id.price)
        var productQuant = findViewById<TextView>(R.id.quantity)

    }
}