package org.feup.apm.viewsdemo

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.TextView

class GridViewActivity : BaseActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    val items = resources.getStringArray(R.array.list_items)

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_grid_view)
    supportActionBar?.setTitle(R.string.act6_name)
    super.createDrawer()

    val selection = findViewById<TextView>(R.id.gvselection)
    val grid = findViewById<GridView>(R.id.grid)
    grid.adapter = ArrayAdapter(this, R.layout.grid_cell, items)
    grid.setOnItemClickListener { _, _, pos, _ -> selection.text = items[pos] }
  }
}