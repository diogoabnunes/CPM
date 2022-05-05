package pt.up.feup.cpm.customerapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pt.up.feup.cpm.customerapp.R

class Login : AppCompatActivity() {
    val tvResponse by lazy { findViewById<TextView>(R.id.tv_response) }
    val email by lazy { findViewById<EditText>(R.id.email) }
    val password by lazy { findViewById<EditText>(R.id.password) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login()
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

    fun login(){
        val user = email.text.toString()
        val pass = password.text.toString()
        val login = findViewById<Button>(R.id.login_button)
        login.setOnClickListener {
            if(user.equals("cpm@feup.pt") && pass.equals("feup")) {
                val intent = Intent(this, Home::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "nhe", Toast.LENGTH_LONG)
            }
        }
    }
}