package pt.up.feup.cpm.customerapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import pt.up.feup.cpm.customerapp.R
import android.widget.Button
import android.widget.EditText

class Register : AppCompatActivity() {
    val tvResponse by lazy { findViewById<TextView>(R.id.tv_response) }
    val register by lazy { findViewById<Button>(R.id.register_button) }
    val email by lazy { findViewById<EditText>(R.id.email) }
    val password by lazy { findViewById<EditText>(R.id.password) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun appendText(value: String) {
        runOnUiThread { tvResponse.text = tvResponse.text.toString() + "\n" + value }
    }

    fun writeText(value: String) {
        runOnUiThread { tvResponse.text = value }
    }
    
    fun register(){
        val user =email.toString()
        val pass = password.toString()
        if(user.equals("dijessmar") && pass.equals("djm"))
        {
                        
        }
    }
}