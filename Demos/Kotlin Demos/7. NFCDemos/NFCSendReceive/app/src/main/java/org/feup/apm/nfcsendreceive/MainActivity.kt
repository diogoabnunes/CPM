package org.feup.apm.nfcsendreceive

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

var reply = ""

class MainActivity : AppCompatActivity() {
  private val replyMsg by lazy { findViewById<TextView>(R.id.reply) }
  private val sendMsg by lazy { findViewById<EditText>(R.id.msg) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    replyMsg.text = reply
    findViewById<Button>(R.id.button1).setOnClickListener { onBtSendClick() }
  }

  private fun onBtSendClick() {
    val intent = Intent(this, NfcSendActivity::class.java).apply {
      putExtra("message", sendMsg.text.toString().toByteArray())
      putExtra("format", "application/nfc.feup.apm.message.type1")
    }
    startActivity(intent)
  }

  override fun onResume() {
    super.onResume()
    replyMsg.text = reply
  }
}
