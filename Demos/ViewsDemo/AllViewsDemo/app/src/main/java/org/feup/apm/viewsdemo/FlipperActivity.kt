package org.feup.apm.viewsdemo

import android.os.Bundle
import android.widget.Button
import android.widget.ViewFlipper

class FlipperActivity : BaseActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_flipper)
    supportActionBar?.setTitle(R.string.act3_name)
    super.createDrawer()
    val flipper = findViewById<ViewFlipper>(R.id.details)
    findViewById<Button>(R.id.flip_me).setOnClickListener { flipper.showNext() }
  }
}