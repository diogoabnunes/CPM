package pt.up.feup.cpm.customerapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pt.up.feup.cpm.customerapp.R

class Login : AppCompatActivity() {
    val tvResponse by lazy { findViewById<TextView>(R.id.tv_response) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupHyperlink()
    }

    fun setupHyperlink() {
        val linkTextView = findViewById<TextView>(R.id.activity_main_link)
        linkTextView.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }

    fun appendText(value: String) {
        runOnUiThread { tvResponse.text = tvResponse.text.toString() + "\n" + value }
    }

    fun writeText(value: String) {
        runOnUiThread { tvResponse.text = value }
    }

    fun onclick(view: View) {
        login(
            findViewById<EditText>(R.id.email).text.toString(),
            findViewById<EditText>(R.id.password).text.toString()
        )
    }
    fun login(userName: String, password: String) {
        if (userName == "cpm@feup.pt" && password == "1234") {
            startActivity(Intent(this, Home::class.java))
            Toast.makeText(this, "Login Success!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
        }
    }


}