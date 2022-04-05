package org.feup.apm.dialogfragmentdemo

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialogFragment
import org.feup.apm.dialogfragmentdemo.GetNameDialog.GetNameDialogListener

class MainActivity : AppCompatActivity(), GetNameDialogListener {
  private val tv by lazy { findViewById<TextView>(R.id.tv_hello) }

  /** Called when the activity is first created.  */
  public override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    // Button 'Say hello' listener
    findViewById<Button>(R.id.bt_hello).setOnClickListener {
      GetNameDialogFragment.newInstance(getString(R.string.dlg_item_hint))    // Create and show
        .show(supportFragmentManager, "getname")                          // the Dialog fragment
    }
  }

  /* callback for the dialog button */
  override fun onDoneClick(dlg: GetNameDialog) {
    tv.text = "Hello, ${dlg.edName.text.toString()}!"
  }
}

/* The Dialog fragment class with better behaviour than the plain Dialog
 *     AppCompatDialogFragment is uniform in Android versions
 *     To have a title, we need to override the android:DialogTheme (Fragment don't have a
 *         title by default
 */
class GetNameDialogFragment : AppCompatDialogFragment() {
  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    super.onCreateDialog(savedInstanceState)
    return GetNameDialog(activity as Context, arguments?.getString("hint") ?: "")
  }

  companion object {   // implements a factory to pass arguments (the constructor must not have parameters)
    fun newInstance(nameHint: String): GetNameDialogFragment {
      val frag = GetNameDialogFragment()
      frag.arguments = Bundle().also{ it.putString("hint", nameHint) }
      return frag
    }
  }
}

/*   The plain Dialog class
 *      Calls the activity passed in the constructor when the user clicks 'Done'
 */
class GetNameDialog(val ctx: Context, val nameHint: String): Dialog(ctx) {
  val edName by lazy { findViewById<EditText>(R.id.ed_name) }

  interface GetNameDialogListener {
    fun onDoneClick(dlg: GetNameDialog)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setTitle(R.string.dlg_title)
    setContentView(R.layout.dialog)
    edName.setHint(nameHint)
    findViewById<Button>(R.id.bt_done).setOnClickListener {
      (ctx as GetNameDialogListener).onDoneClick(this)
      dismiss()
    }
  }
}
