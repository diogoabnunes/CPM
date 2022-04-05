package org.feup.apm.viewsdemo

import android.os.Bundle
import android.view.View
import android.widget.AnalogClock
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.FragmentTransaction

class TabActionActivity : BaseActivity(), ActionBar.TabListener {
  private val clock by lazy { findViewById<AnalogClock>(R.id.analogClock1) }
  private val button by lazy { findViewById<Button>(R.id.button1) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_tab_action)
    supportActionBar?.setTitle(R.string.act10_name)
    supportActionBar?.navigationMode = ActionBar.NAVIGATION_MODE_TABS
    super.createDrawer()

    button.setOnClickListener { Toast.makeText(this,"Button pressed", Toast.LENGTH_LONG).show() }
    supportActionBar?.addTab(supportActionBar?.newTab()?.setTabListener(this)?.setText("Clock"))
    supportActionBar?.addTab(supportActionBar?.newTab()?.setTabListener(this)?.setText("Button"))
  }

  override fun onTabReselected(tab: ActionBar.Tab, ft: FragmentTransaction) {}   // not used

  override fun onTabSelected(tab: ActionBar.Tab, ft: FragmentTransaction) {
    when (tab.position) {
      0 -> clock.visibility = View.VISIBLE
      1 -> button.visibility = View.VISIBLE
    }
  }

  override fun onTabUnselected(tab: ActionBar.Tab, ft: FragmentTransaction) {
    when (tab.position) {
      0 -> clock.visibility = View.INVISIBLE
      1 -> button.visibility = View.INVISIBLE
    }
  }
}