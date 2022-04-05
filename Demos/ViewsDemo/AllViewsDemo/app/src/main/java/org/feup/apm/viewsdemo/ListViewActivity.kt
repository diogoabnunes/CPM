package org.feup.apm.viewsdemo

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class ListViewActivity : BaseActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    val items = resources.getStringArray(R.array.list_items)     // the list content (strings)

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_list_view)
    supportActionBar?.setTitle(R.string.act5_name)
    super.createDrawer()
    val selection = findViewById<TextView>(R.id.lvselection)
    val list = findViewById<ListView>(R.id.linearlist)
    list.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
    list.setOnItemClickListener { _, _, pos, _ -> selection.text = items[pos] }
  }
}