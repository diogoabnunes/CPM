package org.feup.apm.viewsdemo

import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import android.widget.ViewFlipper

class FlipperAutoActivity : BaseActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_flipper_auto)
    supportActionBar?.setTitle(R.string.act4_name)
    super.createDrawer()

    val items = resources.getStringArray(R.array.list_items)
    val flipper = findViewById<ViewFlipper>(R.id.vf_details)
    flipper.outAnimation = AnimationUtils.loadAnimation(this, R.anim.pull_left_out)
    flipper.inAnimation = AnimationUtils.loadAnimation(this, R.anim.push_right_in)

    for (item in items) {
      val btn = Button(this)
      btn.text = item
      btn.setOnClickListener { v -> onButtonClicked(v as Button) }
      flipper.addView(
        btn, FrameLayout.LayoutParams(
          ViewGroup.LayoutParams.WRAP_CONTENT,
          ViewGroup.LayoutParams.WRAP_CONTENT,
          Gravity.CENTER
        )
      )
    }
    flipper.flipInterval = 2000
    flipper.startFlipping()
  }

  private fun onButtonClicked(bt: Button) {
    Toast.makeText(this, "Button clicked:\n${bt.text}", Toast.LENGTH_LONG).show()
  }
}