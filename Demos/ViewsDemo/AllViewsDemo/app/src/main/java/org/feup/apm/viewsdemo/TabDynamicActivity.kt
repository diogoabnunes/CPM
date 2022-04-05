package org.feup.apm.viewsdemo

import android.os.Bundle
import android.widget.AnalogClock
import android.widget.Button
import android.widget.TabHost

class TabDynamicActivity : BaseActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_tab_dynamic)
    supportActionBar?.setTitle(R.string.act9_name)
    super.createDrawer()

    val tabs = findViewById<TabHost>(R.id.bartab)
    tabs.setup()
    tabs.addTab(tabs.newTabSpec("buttontab").setContent(R.id.buttontab).setIndicator("Button"))

    findViewById<Button>(R.id.buttontab).setOnClickListener {
      tabs.addTab(tabs.newTabSpec("tag").setContent{ AnalogClock(this) }.setIndicator("Clock"))
    }
  }
}