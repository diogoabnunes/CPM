package org.feup.apm.lunchlist3b

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {
  private val app by lazy { application as LunchApp }
  private val edName by lazy { findViewById<EditText>(org.feup.apm.lunchlist3b.R.id.ed_name) }
  private val edAddress by lazy { findViewById<EditText>(org.feup.apm.lunchlist3b.R.id.ed_address) }
  private val edNotes by lazy { findViewById<EditText>(org.feup.apm.lunchlist3b.R.id.ed_notes) }
  private val rgTypes by lazy { findViewById<RadioGroup>(org.feup.apm.lunchlist3b.R.id.rg_types) }
  private val rPos by lazy { intent.getIntExtra(ID_EXTRA, -1) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    supportActionBar?.setIcon(R.drawable.rest_icon)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    setContentView(R.layout.activity_details)

    findViewById<View>(R.id.bt_save).setOnClickListener { _ -> onBtSaveClick() }
    if (rPos != -1) load()
  }

  private fun onBtSaveClick() {
    val type = when(rgTypes.checkedRadioButtonId) {
      R.id.rb_take -> "take"
      R.id.rb_sit -> "sit"
      R.id.rb_delivery -> "delivery"
      else -> ""
    }
    if (rPos != -1)
      app.adapter!!.remove(app.current)
    app.current = Restaurant(edName.text.toString(), edAddress.text.toString(), type, edNotes.text.toString())
    if (rPos != -1)
      app.adapter!!.insert(app.current, rPos)
    else
      app.adapter!!.add(app.current)
    finish()
  }

  private fun load() {
    edName.setText(app.current?.name)
    edAddress.setText(app.current?.address)
    edNotes.setText(app.current?.notes)
    when (app.current?.type) {
      "sit" -> rgTypes.check(R.id.rb_sit)
      "take" -> rgTypes.check(R.id.rb_take)
      "delivery" -> rgTypes.check(R.id.rb_delivery)
    }
  }
}