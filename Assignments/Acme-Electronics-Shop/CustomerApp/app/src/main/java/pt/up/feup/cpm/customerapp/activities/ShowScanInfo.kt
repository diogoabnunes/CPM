package pt.up.feup.cpm.customerapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import pt.up.feup.cpm.customerapp.R

class ShowScanInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_scan_info)
        var it= intent.getStringExtra("info")
        val textView : TextView = findViewById(R.id.tv_show_info) as TextView
        textView.text=it
    }
}