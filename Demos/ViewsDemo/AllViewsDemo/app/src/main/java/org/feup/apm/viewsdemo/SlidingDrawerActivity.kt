package org.feup.apm.viewsdemo

import android.os.Bundle

class SlidingDrawerActivity : BaseActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sliding_drawer)
    super.createDrawer()
    supportActionBar?.setTitle(R.string.act2_name)
  }
}