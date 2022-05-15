package pt.up.feup.cpm.customerapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import pt.up.feup.cpm.customerapp.R
import pt.up.feup.cpm.customerapp.utils.LoginCustomer

class Login : AppCompatActivity() {
    val email by lazy { findViewById<EditText>(R.id.email) }
    val password by lazy { findViewById<EditText>(R.id.password) }
    val loginButton by lazy { findViewById<Button>(R.id.login_button) }
    val tvResponse by lazy { findViewById<TextView>(R.id.tv_response) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupRegisterButton()
        setupLoginButton()
        setupHomeButton() // TO DELETE
    }

    private fun setupRegisterButton() {
        val linkTextView = findViewById<TextView>(R.id.activity_main_link)
        linkTextView.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }

    private fun setupLoginButton() {
        loginButton.setOnClickListener {
            val email = email.text.toString()
            val password = password.text.toString()

            var js = JSONObject()
            js.accumulate("email", email)
            js.accumulate("password", password)

            Thread(LoginCustomer(this, js.toString())).start()

            if (email == "cpm@feup.pt" && password == "1234") {
                startActivity(Intent(this, Home::class.java))
                Toast.makeText(this, "Login Success!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // TO DELETE
    private fun setupHomeButton() {
        val linkTextView = findViewById<TextView>(R.id.activity_link)
        linkTextView.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }

    fun appendText(value: String) {
        runOnUiThread { tvResponse.text = tvResponse.text.toString() + "\n" + value }
    }

    fun writeText(value: String) {
        runOnUiThread { tvResponse.text = value }
    }
}