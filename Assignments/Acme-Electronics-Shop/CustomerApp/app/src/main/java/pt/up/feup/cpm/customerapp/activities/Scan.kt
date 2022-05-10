package pt.up.feup.cpm.customerapp.activities

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import pt.up.feup.cpm.customerapp.R

private const val ACTION_SCAN = "com.google.zxing.client.android.SCAN"

class Scan : AppCompatActivity() {
    private val tvMessage by lazy { findViewById<TextView>(R.id.tv_message) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)
        findViewById<Button>(R.id.bt_scan_qr).setOnClickListener { _ -> scan(true) }
        findViewById<Button>(R.id.bt_scan_bar).setOnClickListener { _ -> scan(false) }
    }
    override fun onSaveInstanceState(bundle: Bundle) {
        bundle.putCharSequence("Message", tvMessage.text)
        super.onSaveInstanceState(bundle)
    }

    override fun onRestoreInstanceState(bundle: Bundle) {
        super.onRestoreInstanceState(bundle)
        tvMessage.text = bundle.getCharSequence("Message")
    }

    fun scan(qrcode: Boolean) {
        try {
            val intent = Intent(ACTION_SCAN)
            intent.putExtra("SCAN_MODE", if (qrcode) "QR_CODE_MODE" else "PRODUCT_MODE")
            startActivity(intent)
            startActivityForResult(intent, 0)
        }
        catch (anfe: ActivityNotFoundException) {
            showDialog(this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                val contents = data?.getStringExtra("SCAN_RESULT") ?: ""
                val format = data?.getStringExtra("SCAN_RESULT_FORMAT") ?: ""
                tvMessage.text = "Format: $format\nMessage: $contents"
            }
        }
    }

    private fun showDialog(act: AppCompatActivity, title: CharSequence, message: CharSequence, buttonYes: CharSequence, buttonNo: CharSequence): AlertDialog {
        val downloadDialog = AlertDialog.Builder(act)
        downloadDialog.setTitle(title)
        downloadDialog.setMessage(message)
        downloadDialog.setPositiveButton(buttonYes) { _, _ ->
            val uri = Uri.parse("market://search?q=pname:com.google.zxing.client.android")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            act.startActivity(intent)
        }
        downloadDialog.setNegativeButton(buttonNo, null)
        return downloadDialog.show()
    }
}