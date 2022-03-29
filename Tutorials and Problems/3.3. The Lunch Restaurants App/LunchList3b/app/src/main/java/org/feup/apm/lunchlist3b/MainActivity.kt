package org.feup.apm.lunchlist3b

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

const val ID_EXTRA = "org.feup.apm.lunchlist.POS"

class MainActivity : AppCompatActivity() {
  private val app by lazy { application as LunchApp }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    supportActionBar?.setIcon(R.drawable.rest_icon)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    setContentView(R.layout.activity_main)
    app.adapter = RestaurantAdapter(this, app.rests)

    val list = findViewById<ListView>(R.id.listview)
    list.adapter = app.adapter
    list.emptyView = findViewById(R.id.empty_list)
    list.setOnItemClickListener { _, _, pos, _ -> onRestItemClick(pos) }
  }

  // the main menu methods

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    MenuInflater(this).inflate(R.menu.main, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.toast -> {
        val message = app.current?.notes ?: "No restaurant selected"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        true
      }
      R.id.add -> {
        startActivity(Intent(this, DetailsActivity::class.java))
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

  // Listeners to events and interactions

  private fun onRestItemClick(pos: Int) {
    app.current = app.rests[pos]
    startActivity(Intent(this, DetailsActivity::class.java).putExtra(ID_EXTRA, pos))
  }
}

