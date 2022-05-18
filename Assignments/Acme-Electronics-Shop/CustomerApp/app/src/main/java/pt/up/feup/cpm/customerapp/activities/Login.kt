package pt.up.feup.cpm.customerapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import org.json.JSONObject
import pt.up.feup.cpm.customerapp.R
import pt.up.feup.cpm.customerapp.http.LoginCustomer
import pt.up.feup.cpm.customerapp.models.Customer
import kotlinx.coroutines.*

class Login : AppCompatActivity() {
    val loginButton by lazy { findViewById<Button>(R.id.login_button) }
    var tvResponse = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupRegisterButton()
        setupLoginButton()
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
            val email by lazy { findViewById<EditText>(R.id.email) }
            val password by lazy { findViewById<EditText>(R.id.password) }

            val js = JSONObject()
            js.accumulate("email", email.text.toString())
            js.accumulate("password", password.text.toString())

            try {
                runBlocking {
                    launch {
                        Thread(LoginCustomer(this@Login, js.toString())).start()
                    }
                    delay(1000L)

                    val customerRes = JSONObject(tvResponse)
                    val customer = Gson().fromJson(customerRes.toString(), Customer::class.java)

                    if (customer != null) {
                        val intent = Intent(this@Login, Home::class.java)
                        intent.putExtra("email", customer.email)
                        startActivity(intent)
                        Toast.makeText(this@Login, "Login Success!", Toast.LENGTH_LONG).show()
                    }
                    else {
                        Toast.makeText(this@Login, "Login failed...", Toast.LENGTH_LONG).show()
                    }
                }
            }
            catch (e: Exception) {
                Toast.makeText(this@Login, "Failed to connect to Database...", Toast.LENGTH_LONG).show()
            }
        }
    }
}