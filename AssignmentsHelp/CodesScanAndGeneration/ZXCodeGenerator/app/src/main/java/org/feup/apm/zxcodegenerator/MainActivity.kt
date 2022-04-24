package org.feup.apm.zxcodegenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
  val ed_number by lazy { findViewById<EditText>(R.id.ed_number) }
  val ed_msg by lazy { findViewById<EditText>(R.id.ed_msg) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    findViewById<Button>(R.id.bt_bar).setOnClickListener { vw -> onButtonClick(vw) }
    findViewById<Button>(R.id.bt_qr).setOnClickListener { vw -> onButtonClick(vw) }
  }

  private fun onButtonClick(vw: View) {
    val intent = Intent(this, ShowCodeActivity::class.java)
    when (vw.id) {
      R.id.bt_bar -> { intent.putExtra("type", 0)
                       val nr = if (ed_number.text.isEmpty())
                         "00000000001"
                       else
                         ed_number.text.toString()
                       intent.putExtra("value", nr) }
      R.id.bt_qr -> { intent.putExtra("type", 1)
                      val msg = if (ed_msg.text.isEmpty())
                        "Just a simple text message"
                      else
                        ed_msg.text.toString()
                      intent.putExtra("value", msg) }
    }
    startActivity(intent)
  }
}