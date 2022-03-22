package org.feup.apm.lunchlist1c

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
  private val rests = arrayListOf<Restaurant>()
  private val adapter by lazy { ArrayAdapter(this, android.R.layout.simple_list_item_1, rests) }
  private val edName by lazy { findViewById<EditText>(R.id.ed_name) }
  private val edAddress by lazy { findViewById<EditText>(R.id.ed_address) }
  private val rgTypes by lazy { findViewById<RadioGroup>(R.id.rg_types) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    supportActionBar?.setIcon(R.drawable.rest_icon)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    setContentView(R.layout.activity_main)

    findViewById<Button>(R.id.bt_save).setOnClickListener { _ -> onBtSaveClick() }
    val list = findViewById<ListView>(R.id.listview)
    list.adapter = adapter
    list.setOnItemClickListener { _, _, pos, _ -> onRestItemClick(pos) }
  }

  private fun onBtSaveClick() {
    val r = Restaurant()
    r.name = edName.text.toString()
    r.address = edAddress.text.toString()
    r.type = when(rgTypes.checkedRadioButtonId) {
        R.id.rb_take -> "take"
        R.id.rb_sit -> "sit"
        R.id.rb_delivery -> "delivery"
        else -> ""
    }
    adapter.add(r)
  }

  private fun onRestItemClick(pos: Int) {
    val r = rests[pos]
    edName.setText(r.name)
    edAddress.setText(r.address)
    when (r.type) {
      "sit" -> rgTypes.check(R.id.rb_sit)
      "take" -> rgTypes.check(R.id.rb_take)
      "delivery" -> rgTypes.check(R.id.rb_delivery)
    }
  }
}

/*
 *  The Restaurant class, for storing information for each restaurant
 */

class Restaurant {
  var name = ""
  var address = ""
  var type = ""

  override fun toString(): String {
    return name
  }
}
