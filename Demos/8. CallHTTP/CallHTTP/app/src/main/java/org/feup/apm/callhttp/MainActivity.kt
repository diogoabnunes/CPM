package org.feup.apm.callhttp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
  val tvResponse by lazy { findViewById<TextView>(R.id.tv_response) }
  val edtIp by lazy { findViewById<EditText>(R.id.edt_IP) }
  val edtId by lazy { findViewById<EditText>(R.id.edt_id) }
  val edtName by lazy { findViewById<EditText>(R.id.edt_name) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {

    when (item.itemId) {
      R.id.getusers -> {
        Thread(GetUsers(this, edtIp.text.toString())).start()
        return true
      }
      R.id.getuser -> {
        Thread(GetUser(this, edtIp.text.toString(), edtId.text.toString())).start()
        return true
      }
      R.id.adduser -> {
        Thread(AddUser(this, edtIp.text.toString(), edtName.text.toString())).start()
        return true
      }
      R.id.deluser -> {
        Thread(DelUser(this, edtIp.text.toString(), edtId.text.toString())).start()
        return true
      }
      R.id.changeuser -> {
        Thread(ChUser(this, edtIp.text.toString(), edtId.text.toString(), edtName.text.toString())).start()
        return true
      }
      R.id.clear -> {
        tvResponse.setText(R.string.tv_start_value)
        return true
      }
    }
    return super.onOptionsItemSelected(item)
  }

  fun appendText(value: String) {
    runOnUiThread { tvResponse.text = tvResponse.text.toString() + "\n" + value }
  }

  fun writeText(value: String) {
    runOnUiThread { tvResponse.text = value }
  }
}