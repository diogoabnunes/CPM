package org.feup.apm.firstapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

const val EXTRA_MESSAGE = "org.feup.apm.firstapp.MESSAGE"

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    findViewById<Button>(R.id.bt_send).setOnClickListener { _ ->
      val message = findViewById<EditText>(R.id.edt_message).text.toString()
      val intent = Intent(this, SecondActivity::class.java)
      intent.putExtra(EXTRA_MESSAGE, message)
      startActivity(intent)
    }
  }
}