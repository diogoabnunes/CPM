package org.feup.apm.nfccard

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
  val tvOutput by lazy { findViewById<TextView>(R.id.text_output) }
  val edAccount by lazy { findViewById<EditText>(R.id.card_account_field) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    tvOutput.setText(R.string.tv_state_ready)
    edAccount.setText(AccountNrStore.getAccount(this))
    findViewById<Button>(R.id.button_save).setOnClickListener { onBtSaveClick() }
  }

  private fun onBtSaveClick() {
    var accNr = edAccount.text.toString()
    val len = accNr.length
    if (len < 8) {
      var res = ""
      for (k in 0 until 8 - len) res += "0"
      accNr = res + accNr
    }
    else if (len > 8) accNr = accNr.substring(0, 8)
    AccountNrStore.setAccount(this, tvOutput, accNr)
    edAccount.setText(AccountNrStore.getAccount(this))
  }
}
