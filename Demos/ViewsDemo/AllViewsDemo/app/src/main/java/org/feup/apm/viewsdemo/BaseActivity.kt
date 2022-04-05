package org.feup.apm.viewsdemo

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout

open class BaseActivity : AppCompatActivity() {
  private val mDrawerLayout by lazy { findViewById<DrawerLayout>(R.id.drawer_layout) }
  private val mDrawerList by lazy { findViewById<ListView>(R.id.left_drawer) }
  private val mDrawerToggle by lazy { ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name) }

  fun createDrawer() {
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setHomeButtonEnabled(true)

    // options and icon ids should be in equal number
    val options: Array<String> = resources.getStringArray(R.array.options_array)

    // sample stock (Android) icons
    val icons = intArrayOf(
      android.R.drawable.ic_menu_day,
      android.R.drawable.ic_menu_report_image,
      android.R.drawable.ic_menu_more,
      android.R.drawable.ic_menu_slideshow,
      android.R.drawable.ic_menu_info_details,
      android.R.drawable.ic_menu_crop,
      android.R.drawable.ic_menu_edit,
      android.R.drawable.ic_menu_call,
      android.R.drawable.ic_menu_directions,
      android.R.drawable.ic_menu_gallery,
      android.R.drawable.ic_menu_help,
      android.R.drawable.ic_menu_agenda,
      android.R.drawable.ic_menu_month,
      android.R.drawable.ic_menu_search
    )
    mDrawerList.adapter = ItemsAdapter(options, icons)
    mDrawerList.setOnItemClickListener { _, _, pos, _ -> onDrawerItemClick(pos)}
    mDrawerLayout.addDrawerListener(mDrawerToggle)
  }

  private fun onDrawerItemClick(pos: Int) {
    val intent = when (pos) {
      0 -> Intent(this, ChronoActivity::class.java)
      1 -> Intent(this, SlidingDrawerActivity::class.java)
      2 -> Intent(this, FlipperActivity::class.java)
      3 -> Intent(this, FlipperAutoActivity::class.java)
      4 -> Intent(this, ListViewActivity::class.java)
      5 -> Intent(this, GridViewActivity::class.java)
      6 -> Intent(this, ProgressActivity::class.java)
      7 -> Intent(this, TabPlainActivity::class.java)
      8 -> Intent(this, TabDynamicActivity::class.java)
      9 -> Intent(this, TabActionActivity::class.java)
      10 -> Intent(this, SnackBarActivity::class.java)
      11 -> Intent(this, SwipeActivity::class.java)
      12 -> Intent(this, RecyclerActivity::class.java)
      13 -> Intent(this, ShowSettingsActivity::class.java)
      else -> null
    }
    mDrawerLayout.closeDrawer(mDrawerList)
    startActivity(intent)
    if (this !is MainActivity)
      finish()
  }

  /* required by ActionBarDrawerToggle */
  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return mDrawerToggle.onOptionsItemSelected(item)
  }

  /* required by ActionBarDrawerToggle */
  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)
    mDrawerToggle.syncState()
  }

  /* required by ActionBarDrawerToggle */
  override fun onConfigurationChanged(newConfig: Configuration) {
    super.onConfigurationChanged(newConfig)
    mDrawerToggle.onConfigurationChanged(newConfig)
  }

  /* internal class as a custom adapter for displaying items on the drawer list *
   *
   */
  private inner class ItemsAdapter(val names: Array<String>, val iconIds: IntArray): ArrayAdapter<String>(this, R.layout.drawer_list_item, names) {
    override fun getView(pos: Int, convertView: View?, parent: ViewGroup): View {
      val row = convertView ?: layoutInflater.inflate(R.layout.drawer_list_item, parent, false)
      row.findViewById<TextView>(R.id.item).text = names[pos]                   // item name
      row.findViewById<ImageView>(R.id.icon).setImageResource(iconIds[pos])     // item icon
      return row
    }
  }
}