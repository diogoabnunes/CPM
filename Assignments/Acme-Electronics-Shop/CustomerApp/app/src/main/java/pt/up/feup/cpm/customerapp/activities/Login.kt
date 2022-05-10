package pt.up.feup.cpm.customerapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import pt.up.feup.cpm.customerapp.R
import pt.up.feup.cpm.customerapp.utils.*

class Login : AppCompatActivity() {
    val tvResponse by lazy { findViewById<TextView>(R.id.tv_response) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupHyperlink()
        setupLoginButton()

        //go to Home directly--REMOVER
        val linkTextView = findViewById<TextView>(R.id.activity_link)
        linkTextView.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
        //---------------------

        findViewById<Button>(R.id.bt_qr).setOnClickListener { vw -> onButtonClick(vw) }


        val linkTextView2 = findViewById<TextView>(R.id.bt_scan)
        linkTextView2.setOnClickListener {
            val intent = Intent(this, Scan::class.java)
            startActivity(intent)
        }
        //---------------------

    }
    private fun onButtonClick(vw: View) {
        val title: String
        val author: String
        val categories: List<String>

        title="Hello"
        author="World"
        categories= listOf("123","me llamo jeff")


        val intent = Intent(this, ShowQR::class.java)
        when (vw.id) {
            R.id.bt_qr -> { intent.putExtra("type", 1)

                val value="Category [title: ${title}, author: ${author}, categories: ${categories}]"
                intent.putExtra("value", value)
            }
        }
        startActivity(intent)
    }


    fun setupHyperlink() {
        val linkTextView = findViewById<TextView>(R.id.activity_main_link)
        linkTextView.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }

    fun setupLoginButton() {
        val loginButton = findViewById<Button>(R.id.login_button)
//        loginButton.setOnClickListener {
//            val userName = findViewById<EditText>(R.id.email).text.toString()
//            val password = findViewById<EditText>(R.id.password).text.toString()
//            if (userName == "cpm@feup.pt" && password == "1234") {
//                startActivity(Intent(this, Home::class.java))
//                Toast.makeText(this, "Login Success!", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
//            }
//        }
        loginButton.setOnClickListener {
            Thread(GetProducts()).start()
        }
    }

    fun appendText(value: String) {
        runOnUiThread { tvResponse.text = tvResponse.text.toString() + "\n" + value }
    }

    fun writeText(value: String) {
        runOnUiThread { tvResponse.text = value }
    }
}