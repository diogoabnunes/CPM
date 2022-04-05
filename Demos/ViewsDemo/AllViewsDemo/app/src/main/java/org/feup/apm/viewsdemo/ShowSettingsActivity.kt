package org.feup.apm.viewsdemo

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

class ShowSettingsActivity : BaseActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_show_settings)
    supportActionBar?.setTitle(R.string.act14_name)
    super.createDrawer()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.prefs_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.mn_settings) {
      startActivity(Intent(this, EditSettingsActivity::class.java))
      return true
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onStart() {
    super.onStart()
    showUserSettings()
  }

  private fun showUserSettings() {
    val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)
    var dependent: Boolean

    var prefs = "\n Checkbox Pref: ${sharedPrefs.getBoolean("checkbox_preference", false)}"
    prefs += "\n String Pref: ${sharedPrefs.getString("edittext_preference", "")}"
    prefs += "\n List Pref: ${sharedPrefs.getString("list_preference", "")}"
    prefs += "\n Parent checkbox: " + sharedPrefs.getBoolean("parent_checkbox_preference", false).also{ dependent = it }
    if (dependent)
      prefs += "\n    Child checkbox: ${sharedPrefs.getBoolean("child_checkbox_preference", false)}"
    prefs += "\n Switch in another screen: ${sharedPrefs.getBoolean("next_screen_checkbox_preference", false)}"
    findViewById<TextView>(R.id.tv_values).text = prefs
  }
}