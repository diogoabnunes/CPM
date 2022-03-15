package org.feup.apm.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val tv = TextView(this)
    tv.text = intent.getStringExtra(EXTRA_MESSAGE)
    tv.textSize = 30.0f
    setContentView(tv)
  }
}