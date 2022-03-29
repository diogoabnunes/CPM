package pt.up.feup.dn.lunchlist1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setIcon(R.drawable.rest_icon)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button_save).setOnClickListener { _ -> onButtonSaveClick() }
    }

    private fun onButtonSaveClick() {
        val r = Restaurant()
        r.name = findViewById<EditText>(R.id.edit_name).text.toString()
        r.address = findViewById<EditText>(R.id.edit_address).text.toString()
    }
}