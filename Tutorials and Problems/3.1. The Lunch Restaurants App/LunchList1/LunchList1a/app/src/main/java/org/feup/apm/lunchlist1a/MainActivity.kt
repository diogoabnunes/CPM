package org.feup.apm.lunchlist1a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    supportActionBar?.setIcon(R.drawable.rest_icon)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    setContentView(R.layout.activity_main)
    findViewById<Button>(R.id.bt_save).setOnClickListener { _ -> onBtSaveClick() }
  }

  private fun onBtSaveClick() {
    val r = Restaurant()
    r.name = findViewById<EditText>(R.id.ed_name).text.toString()
    r.address = findViewById<EditText>(R.id.ed_address).text.toString()
  }
}

class Restaurant {
  var name = ""
  var address = ""
}