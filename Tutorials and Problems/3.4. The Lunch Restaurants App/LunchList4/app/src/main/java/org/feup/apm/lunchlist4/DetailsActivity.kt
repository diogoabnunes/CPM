package org.feup.apm.lunchlist4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {
  private val dbHelper by lazy { RestaurantHelper(this) }
  private val rId: String? by lazy { intent.getStringExtra(ID_EXTRA) }
  private val edName by lazy { findViewById<EditText>(R.id.ed_name) }
  private val edAddress by lazy { findViewById<EditText>(R.id.ed_address) }
  private val edNotes by lazy { findViewById<EditText>(R.id.ed_notes) }
  private val rgTypes by lazy { findViewById<RadioGroup>(R.id.rg_types) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    supportActionBar?.setIcon(R.drawable.rest_icon)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    setContentView(R.layout.activity_details)

    findViewById<Button>(R.id.bt_save).setOnClickListener { _ -> onBtSaveClick() }
    if (rId != null) load()
  }

  override fun onDestroy() {
    super.onDestroy()
    dbHelper.close()
  }

  private fun onBtSaveClick() {
    val rName = edName.text.toString()
    val rAddress = edAddress.text.toString()
    val rNotes = edNotes.text.toString()
    val rType = when (rgTypes.checkedRadioButtonId) {
      R.id.sit -> "sit"
      R.id.take -> "take"
      R.id.delivery -> "delivery"
      else -> ""
    }
    if (rId == null)
      currentId = dbHelper.insert(rName, rAddress, rType, rNotes)
    else
      dbHelper.update(rId!!, rName, rAddress, rType, rNotes)
    finish()
  }

  private fun load() {
    val c = dbHelper.getById(rId!!)
    c.moveToFirst()
    edName.setText(dbHelper.getName(c))
    edAddress.setText(dbHelper.getAddress(c))
    edNotes.setText(dbHelper.getNotes(c))
    when (dbHelper.getType(c)) {
      "sit" -> rgTypes.check(R.id.sit)
      "take" -> rgTypes.check(R.id.take)
      "delivery" -> rgTypes.check(R.id.delivery)
    }
    c.close()
  }
}