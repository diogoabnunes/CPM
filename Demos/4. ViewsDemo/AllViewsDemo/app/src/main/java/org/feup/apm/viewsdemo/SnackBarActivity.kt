package org.feup.apm.viewsdemo

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class SnackBarActivity : BaseActivity() {
  private val coordinatorLayout by lazy { findViewById<CoordinatorLayout>(R.id.coordinatorLayout) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_snack_bar)
    supportActionBar?.setTitle(R.string.act11_name)
    super.createDrawer()

    findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { v ->
      val sb = Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null)
      sb.view.setBackgroundResource(R.color.colorPrimary)
      sb.show()
    }
    findViewById<Button>(R.id.btnSimpleSnackbar).setOnClickListener {
      val sb = Snackbar.make(coordinatorLayout, "Welcome to a discardable SnackBar", Snackbar.LENGTH_LONG)
      sb.view.setBackgroundResource(R.color.colorPrimaryDark)
      sb.show()
    }
    findViewById<Button>(R.id.btnActionCallback).setOnClickListener {
      val sb = Snackbar.make(coordinatorLayout, "Message is deleted", Snackbar.LENGTH_LONG).setAction("UNDO") {
        val sb1 = Snackbar.make(coordinatorLayout,"Message is restored!", Snackbar.LENGTH_SHORT)
        sb1.view.setBackgroundResource(R.color.colorAccent)
        sb1.show()
      }
      sb.view.setBackgroundResource(R.color.colorPrimaryDark)
      sb.show()
    }
    findViewById<Button>(R.id.btnCustomSnackbar).setOnClickListener {
      val sb = Snackbar.make(coordinatorLayout, "No internet connection!", Snackbar.LENGTH_LONG).setAction("RETRY") {
          Toast.makeText(this, "RETRY clicked!", Toast.LENGTH_SHORT).show()
      }
      // Changing message text color
      sb.setActionTextColor(Color.GREEN)
      // Changing background and action button text color
      sb.view.setBackgroundResource(R.color.colorHighLight)
      sb.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).setTextColor(Color.YELLOW)
      sb.show()
    }
  }
}