package pt.up.feup.cpm.customerapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import pt.up.feup.cpm.customerapp.R
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import org.json.JSONObject
import pt.up.feup.cpm.customerapp.models.Customer
import pt.up.feup.cpm.customerapp.utils.AddCustomer
import java.time.format.DateTimeFormatter
import java.util.*

class Register : AppCompatActivity() {
    val name by lazy { findViewById<EditText>(R.id.name) }
    val address by lazy { findViewById<EditText>(R.id.address) }
    val fiscal_number by lazy { findViewById<EditText>(R.id.fiscal_number) }
    val email by lazy { findViewById<EditText>(R.id.email) }
    val password by lazy { findViewById<EditText>(R.id.password) }
    val card_type by lazy { findViewById<RadioGroup>(R.id.card_type) }
    val card_number by lazy { findViewById<EditText>(R.id.card_number) }
    val card_validity by lazy { findViewById<EditText>(R.id.card_validity) }
    val register_button by lazy { findViewById<Button>(R.id.register_button) }
    val tvResponse by lazy { findViewById<TextView>(R.id.tv_response) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_button.setOnClickListener {
            val selected = card_type.checkedRadioButtonId
            var type: String? = null
            type = if (selected == R.id.credit_card) "Credit"
            else "Debit"

            var js = JSONObject();
            js.accumulate("name", name.text.toString())
            js.accumulate("address", address.text.toString())
            js.accumulate("fiscalNumber", fiscal_number.text.toString())
            js.accumulate("email", email.text.toString())
            js.accumulate("password", password.text.toString())
            js.accumulate("cardType", type)
            js.accumulate("cardNumber", card_number.text.toString())
            js.accumulate("cardValidity", card_validity.text.toString())

            Thread(AddCustomer(this, js.toString())).start()
        }
    }

    fun appendText(value: String) {
        runOnUiThread { tvResponse.text = tvResponse.text.toString() + "\n" + value }
    }

    fun writeText(value: String) {
        runOnUiThread { tvResponse.text = value }
    }
}