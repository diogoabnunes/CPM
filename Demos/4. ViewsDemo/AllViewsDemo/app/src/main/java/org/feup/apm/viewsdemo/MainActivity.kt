package org.feup.apm.viewsdemo

import android.os.Bundle

class MainActivity : BaseActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    super.createDrawer()
  }
}