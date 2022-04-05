package org.feup.apm.nfcsendreceive

import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NfcReceiveActivity : AppCompatActivity() {
  val tv by lazy { findViewById<TextView>(R.id.textView1) }

  public override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_nfc_receive)
  }

  public override fun onResume() {
    super.onResume()
    if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action) {
      processIntent(intent)
    }
  }

  public override fun onNewIntent(intent: Intent) {
    super.onNewIntent(intent)
    setIntent(intent)
  }

  private fun processIntent(intent: Intent) {
    val rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
    val msg = rawMsgs!![0] as NdefMessage
    reply = String(msg.records[0].payload)
    tv.text = reply
  }
}
