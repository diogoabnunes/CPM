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
    val email by lazy { findViewById<EditText>(R.id.email) }
    val password by lazy { findViewById<EditText>(R.id.password) }

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

    fun onclick(view: View) {
        login(
            email.text.toString(),
            password.text.toString()
        )
    }
    fun login(email: String, password: String) {
        if (email == "cpm@feup.pt" && password == "1234") {
            startActivity(Intent(this, Home::class.java))
            Toast.makeText(this, "Login Success!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
        }
    }
}