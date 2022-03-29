package org.feup.apm.lunchlist3a

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import java.io.Serializable

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {
  private val rests = arrayListOf<Restaurant>()
  private var current: Restaurant? = null
  private val adapter by lazy { RestaurantAdapter() }
  private val edName by lazy { findViewById<EditText>(R.id.ed_name) }
  private val edAddress by lazy { findViewById<EditText>(R.id.ed_address) }
  private val edNotes by lazy { findViewById<EditText>(R.id.ed_notes) }
  private val rgTypes by lazy { findViewById<RadioGroup>(R.id.rg_types) }
  private val tabs by lazy { findViewById<TabLayout>(R.id.tabs) }
  private val listTab by lazy { tabs.newTab().setText("List") }
  private val detailsTab by lazy { tabs.newTab().setText("Details") }
  private val tab1 by lazy { findViewById<ListView>(R.id.listview) }
  private val tab2 by lazy { findViewById<View>(R.id.rest_params) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    supportActionBar?.setIcon(R.drawable.rest_icon)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    setContentView(R.layout.activity_main)

    findViewById<Button>(R.id.bt_save).setOnClickListener { _ -> onBtSaveClicked() }
    tabs.addTab(listTab)
    tabs.addTab(detailsTab)
    tabs.addOnTabSelectedListener(this)
    tab1.adapter = adapter
    tab1.setOnItemClickListener { _, _, pos, _ -> onRestItemClick(pos) }
  }

  // the rotate save/restore and main menu methods

  override fun onSaveInstanceState(state: Bundle) {
    state.putSerializable("current", current)
    super.onSaveInstanceState(state)
  }

  override fun onRestoreInstanceState(state: Bundle) {
    super.onRestoreInstanceState(state)
    current = state.getSerializable("current") as Restaurant?
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    MenuInflater(this).inflate(R.menu.main, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.toast) {
      val message = current?.notes ?: "No restaurant selected"
      Toast.makeText(this, message, Toast.LENGTH_LONG).show()
      return true
    }
    return super.onOptionsItemSelected(item)
  }

  // Listeners to events and interactions

  private fun onBtSaveClicked() {
    val type = when(rgTypes.checkedRadioButtonId) {
      R.id.rb_take -> "take"
      R.id.rb_sit -> "sit"
      R.id.rb_delivery -> "delivery"
      else -> ""
    }
    val r = Restaurant(edName.text.toString(), edAddress.text.toString(), type, edNotes.text.toString())
    adapter.add(r)
    current = r
    clearKeyboard(this)
    listTab.select()
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
    edNotes.setText(r.notes)
    current = r
    detailsTab.select()
  }

  override fun onTabSelected(tab: TabLayout.Tab?) {
    when (tab?.position) {
      0 -> tab1.visibility = View.VISIBLE
      1 -> tab2.visibility = View.VISIBLE
    }
  }

  override fun onTabUnselected(tab: TabLayout.Tab?) {
    when (tab?.position) {
      0 -> tab1.visibility = View.INVISIBLE
      1 -> tab2.visibility = View.INVISIBLE
    }
  }

  override fun onTabReselected(tab: TabLayout.Tab?) {
  }

  // util methods

  private fun clearKeyboard(act: Activity) {
    val view = act.findViewById<View>(android.R.id.content)
    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
  }

  // ListView adapter as an inner class

  inner class RestaurantAdapter : ArrayAdapter<Restaurant>(this@MainActivity, R.layout.list_row, rests) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
      val row = convertView ?: layoutInflater.inflate(R.layout.list_row, parent, false)
      val r = rests[position]
      row.findViewById<TextView>(R.id.title).text = r.name
      row.findViewById<TextView>(R.id.address).text = r.address
      val symbol = row.findViewById<ImageView>(R.id.symbol)
      when (r.type) {
        "sit" -> symbol.setImageResource(R.drawable.ball_red)
        "take" -> symbol.setImageResource(R.drawable.ball_yellow)
        "delivery" -> symbol.setImageResource(R.drawable.ball_green)
      }
      return row
    }
  }
}

// The class for a Restaurant data (refactored in Kotlin style with a primary constructor)

data class Restaurant(val name: String, val address: String, val type: String, val notes: String): Serializable {
  override fun toString(): String {
    return name
  }
}