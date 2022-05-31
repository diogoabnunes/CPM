package org.feup.apm.dialogdemo

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup.LayoutParams;
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog

class MainActivity : AppCompatActivity(), GetNameDialog.NameDlgResult {
  private val tv by lazy { findViewById<TextView>(R.id.name) }
  private val dialog by lazy { GetNameDialog(this, getString(R.string.initial_name)) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    findViewById<Button>(R.id.button).setOnClickListener { dialog.show() }
  }

  /* dialog interface for communicating back */
  override fun putResult(name: String) {
    tv.text = getString(R.string.dlg_result_format, name)
  }
}

class GetNameDialog(private val ctx: Context, private val init: String) : AppCompatDialog(ctx) {
  interface NameDlgResult {
    fun putResult(name: String)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setTitle(R.string.dialog_title)
    setContentView(R.layout.dialog)
    window?.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
    val edt = findViewById<EditText>(R.id.yourname)
    edt?.hint = init

    findViewById<Button>(R.id.getnameokbutton)?.setOnClickListener {
      (ctx as NameDlgResult).putResult(edt?.text.toString())
      dismiss()
    }
  }
}