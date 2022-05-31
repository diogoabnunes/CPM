package org.feup.apm.viewsdemo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import java.util.concurrent.atomic.AtomicBoolean

class ProgressActivity : BaseActivity() {
  private val hBar by lazy { findViewById<ProgressBar>(R.id.pb_hor_progbar) }
  private val rBar by lazy { findViewById<ProgressBar>(R.id.pb_round_progbar) }
  private var count = 0
  private val handler : Handler = object : Handler(Looper.getMainLooper()) {
    override fun handleMessage(msg: Message) {
      hBar.incrementProgressBy(5)
      if (msg.what == 1)
        rBar.visibility = View.INVISIBLE
    }
  }
  private val isRunning = AtomicBoolean(false)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_progress)
    supportActionBar?.setTitle(R.string.act7_name)
    super.createDrawer()
    hBar.progress = 0
    findViewById<Button>(R.id.bt_progress).setOnClickListener { startActivity(Intent(this, ProgressNavigateActivity::class.java)) }
  }

  override fun onStart() {
    super.onStart()
    if (count < 20)
      rBar.visibility = View.VISIBLE
    val background = Thread {
      try {
        while (count < 20 && isRunning.get()) {
          Thread.sleep(1000)
          val msg = handler.obtainMessage(0)
          if (count == 19)
            msg.what = 1
          handler.sendMessage(msg)
          count++
        }
      } catch (t: Throwable) {   // just end the thread
        isRunning.set(false)
      }
      isRunning.set(false)
    }
    isRunning.set(true)
    background.start()
  }

  override fun onStop() {
    super.onStop()
    isRunning.set(false)
  }
}