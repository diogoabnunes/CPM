package org.feup.apm.viewsdemo

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerActivity : BaseActivity(), RowHolder.OnClickedRowListener {
  private val items by lazy { resources.getStringArray(R.array.list_items) }     // the list content (strings)
  private val adapter by lazy { ItemAdapter(this, items) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_recycler)
    supportActionBar?.setTitle(R.string.act13_name)
    super.createDrawer()

    val rv = findViewById<RecyclerView>(R.id.rcview)
    rv.setHasFixedSize(true)
    rv.layoutManager = LinearLayoutManager(this)
    rv.adapter = adapter
  }

  override fun onRowClicked(pos: Int) {
    findViewById<TextView>(R.id.tv_selection).text = adapter.getItem(pos)
  }

  internal inner class ItemAdapter(val context: Context, val list: Array<String>) : RecyclerView.Adapter<RowHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, vType: Int): RowHolder {
      return RowHolder(context, layoutInflater.inflate(R.layout.rc_row, parent, false))
    }

    override fun onBindViewHolder(holder: RowHolder, pos: Int) {
      holder.bindData(list[pos])
    }

    override fun getItemCount(): Int {
      return list.size
    }

    fun getItem(pos: Int): String {
      return list[pos]
    }
  }
}

class RowHolder(val context: Context, val row: View) :  RecyclerView.ViewHolder(row) {
  interface OnClickedRowListener {
    fun onRowClicked(position: Int)
  }

  private val label = row.findViewById<TextView>(R.id.label)
  private val size = row.findViewById<TextView>(R.id.size)
  private val icon = row.findViewById<ImageView>(R.id.icon)
  private val template = size.context.getString(R.string.sz_template)

  init {
    row.setOnClickListener {
      if (context is OnClickedRowListener)
        context.onRowClicked(adapterPosition)
    }
  }

  fun bindData(word: String) {
    label.text = word
    size.text = String.format(template, word.length)
    if (word.length > 4)
      icon.setImageResource(android.R.drawable.ic_delete)
    else
      icon.setImageResource(android.R.drawable.ic_input_add)
  }
}