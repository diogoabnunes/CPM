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
import com.google.android.material.textfield.TextInputEditText
import pt.up.feup.cpm.customerapp.R

class Login : AppCompatActivity() {
    val tvResponse by lazy { findViewById<TextView>(R.id.tv_response) }
    /*val loginbtn = findViewById<Button>(R.id.login_button)
    val email by lazy { findViewById<EditText>(R.id.email) }
    val password by lazy { findViewById<EditText>(R.id.password) }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //login()
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

    /*fun login(){
        loginbtn.setOnClickListener(){
            if(email.text.toString().equals("cpm@feup.pt") && password.text.equals("feup")) {
                val intent = Intent(this, Home::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "LOGIN FAILED!!", Toast.LENGTH_SHORT).show()
            }
        }
    }*/
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