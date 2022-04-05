package org.feup.apm.fragmentplanets

import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
      finish()

    val details = DetailsFragment.newInstance(intent.extras!!)
    supportFragmentManager.beginTransaction()
      .add(android.R.id.content, details)
      .commit()
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> {
        finish()
        return true
      }
    }
    return super.onOptionsItemSelected(item)
  }
}