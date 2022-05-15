package pt.up.feup.cpm.printerterminal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class ShowScanInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_scan_info)
        var it= intent.getStringExtra("info")
        //Toast.makeText(this, "Scan result: ${it}", Toast.LENGTH_LONG).show()
        val textView = findViewById<TextView>(R.id.tv_show_info)
        textView.text=it
    }
}