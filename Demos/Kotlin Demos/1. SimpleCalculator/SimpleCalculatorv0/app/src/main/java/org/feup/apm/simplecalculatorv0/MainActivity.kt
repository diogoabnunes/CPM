package org.feup.apm.simplecalculatorv0

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
  private var memory = 0.0
  private val data1 by lazy { findViewById<EditText>(R.id.editText1) }
  private val data2 by lazy { findViewById<EditText>(R.id.editText2) }
  private val result by lazy { findViewById<TextView>(R.id.result) }

  /** Called when the activity is first created.  */
  public override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  /** Button listeners  */
  fun PlusOnClick(v: View?) {
    val df = DecimalFormat("#.###")
    val a1 = data1.text.toString().toDouble()
    val a2 = data2.text.toString().toDouble()
    result.text = df.format(a1 + a2)
  }

  fun MinusOnClick(v: View?) {
    val df = DecimalFormat("#.###")
    val a1 = data1.text.toString().toDouble()
    val a2 = data2.text.toString().toDouble()
    result.text = df.format(a1 - a2)
  }

  fun MultiplyOnClick(v: View?) {
    val df = DecimalFormat("#.###")
    val a1 = data1.text.toString().toDouble()
    val a2 = data2.text.toString().toDouble()
    result.text = df.format(a1 * a2)
  }

  fun DivideOnClick(v: View?) {
    val df = DecimalFormat("#.###")
    val a1 = data1.text.toString().toDouble()
    val a2 = data2.text.toString().toDouble()
    result.text = df.format(a1 / a2)
  }

  fun StoreOnClick(v: View?) {
    memory = if (result.text.isNotEmpty())
               result.text.toString().toDouble()
             else
               0.0
  }

  fun RecallOnClick(v: View?) {
    val df = DecimalFormat("#.###")
    data1.setText(df.format(memory))
  }
}