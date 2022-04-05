package org.feup.apm.nfcsendreceive

import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.NfcAdapter.OnNdefPushCompleteCallback
import android.nfc.NfcEvent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.nio.charset.Charset


class NfcSendActivity : AppCompatActivity(), OnNdefPushCompleteCallback {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    setContentView(R.layout.activity_nfc_send)

    // Check for available NFC Adapter
    val mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
    if (mNfcAdapter == null) {
      Toast.makeText(applicationContext, "NFC is not available on this device.", Toast.LENGTH_LONG).show()
      finish()
    }
    val tag = intent.extras?.getString("format") ?: ""
    val message = intent.extras?.getByteArray("message")
    val nfcMsg = NdefMessage(arrayOf(createMimeRecord(tag, message)))

    // Register a NDEF message to be sent in a beam operation (P2P)
    if (mNfcAdapter != null) {
      mNfcAdapter.setNdefPushMessage(nfcMsg, this)
      mNfcAdapter.setOnNdefPushCompleteCallback(this, this)
    }
    reply = "Last op was a send."
  }

  override fun onNdefPushComplete(arg0: NfcEvent) {
    runOnUiThread {
      Toast.makeText(applicationContext, "Message sent.", Toast.LENGTH_LONG).show()
      finish()
    }
  }

  private fun createMimeRecord(mimeType: String, payload: ByteArray?): NdefRecord {
    val mimeBytes = mimeType.toByteArray(Charset.forName("ISO-8859-1"))
    return NdefRecord(NdefRecord.TNF_MIME_MEDIA, mimeBytes, ByteArray(0), payload)
  }
}