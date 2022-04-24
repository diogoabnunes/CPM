package pt.up.feup.cpm.customerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupHyperlink()

        //email.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.vector_mail, 0, 0, 0)
        //password.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.vector_locker, 0, 0, 0)
    }

    private fun setupHyperlink() {
        val linkTextView = findViewById<TextView>(R.id.activity_main_link)
        linkTextView.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }
}