package org.feup.apm.lunchlist1b

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

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
    r.type = when (findViewById<RadioGroup>(R.id.rg_types).checkedRadioButtonId) {
               R.id.rb_take -> "take"
               R.id.rb_sit -> "sit"
               R.id.rb_delivery -> "delivery"
               else -> ""
             }
  }
}

class Restaurant {
  var name = ""
  var address = ""
  var type = ""
}
