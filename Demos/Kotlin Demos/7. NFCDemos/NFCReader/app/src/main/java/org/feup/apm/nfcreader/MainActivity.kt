package org.feup.apm.nfcreader

import android.nfc.NfcAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

const val READER_FLAGS = NfcAdapter.FLAG_READER_NFC_A or NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK

class MainActivity : AppCompatActivity(), CardReader.CardReaderCallback {
  val nfc by lazy { NfcAdapter.getDefaultAdapter(this) }
  val tvAccNr by lazy { findViewById<TextView>(R.id.card_account_field) }
  val cardReader by lazy { CardReader(this) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    if (nfc == null) {
      Toast.makeText(this, "NFC adapter not present!", Toast.LENGTH_LONG).show()
      finish()
    }
    findViewById<Button>(R.id.bt_clear).setOnClickListener { tvAccNr.setText(R.string.tv_accnr) }
  }

  override fun onPause() {
    super.onPause()
    nfc.disableReaderMode(this)
  }

  override fun onResume() {
    super.onResume()
    nfc.enableReaderMode(this, cardReader, READER_FLAGS, null)
  }

  override fun onCardNrReceived(accountNr: String) {
    runOnUiThread { tvAccNr.setText(accountNr) }
  }
}