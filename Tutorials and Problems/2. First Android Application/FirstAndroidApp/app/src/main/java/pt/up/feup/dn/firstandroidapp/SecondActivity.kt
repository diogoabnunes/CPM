package pt.up.feup.dn.firstandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create a TextView and fill it with the string that comes on the Intent
        val tv = TextView(this)
        tv.text = intent.getStringExtra(EXTRA_MESSAGE)
        tv.textSize = 30.0F

        // Set the TextView as the Activity screen
        setContentView(tv)
    }
}