package org.feup.apm.viewsdemo

import android.os.Bundle
import android.widget.Button
import android.widget.TabHost
import android.widget.Toast

class TabPlainActivity : BaseActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_tab_plain)
    supportActionBar?.setTitle(R.string.act8_name)
    super.createDrawer()

    val tabs = findViewById<TabHost>(R.id.tabhost)
    tabs.setup()
    tabs.addTab(tabs.newTabSpec("tag1").setContent(R.id.tab1).setIndicator("Clock"))
    tabs.addTab(tabs.newTabSpec("tag2").setContent(R.id.tab2).setIndicator("Button"))

    findViewById<Button>(R.id.tab2).setOnClickListener { Toast.makeText(this,"Button clicked!", Toast.LENGTH_LONG).show() }
  }
}