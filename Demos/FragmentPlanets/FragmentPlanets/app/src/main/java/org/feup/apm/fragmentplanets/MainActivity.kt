package org.feup.apm.fragmentplanets

import androidx.fragment.app.FragmentManager
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.main_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.about) {
      startActivity(Intent(this, AboutActivity::class.java))
      return true
    }
    return super.onOptionsItemSelected(item)
  }

  fun isMultiPane(): Boolean {
    return resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
  }

  /*
   * Helper function to show the details of a selected item, either by
   * displaying a fragment in-place in the current UI, or starting a
   * whole new activity in which is displayed.
   */
  fun showDetails(index: Int) {
    if (isMultiPane()) {
      // Check what fragment is shown, replace if needed.
      var details = supportFragmentManager.findFragmentById(R.id.details) as DetailsFragment?
      if (details == null || details.mIndex !== index) {
        details = DetailsFragment.newInstance(index) // Make new fragment to show this selection.
        // Execute a transaction, replacing any existing fragment inside the frame with the new one.
        val ft = supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right)
        //ft.setCustomAnimations(R.animator.bounce_in_down, R.animator.slide_out_right)   // other replacement animations
        //ft.setCustomAnimations(R.animator.fade_in, R.animator.fade_out)
        //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        ft.replace(R.id.details, details)
        ft.addToBackStack("details")
        ft.commit()
      }
    } else {
      // Otherwise we need to launch a new activity
      // but first empty the fragment stack
      supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
      val intent = Intent(this, DetailsActivity::class.java).also { it.putExtra("index", index) }
      startActivity(intent)
    }
  }
}