package org.feup.apm.lunchlist3b

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

// The class for a Restaurant data (refactored in Kotlin style with a primary constructor)

data class Restaurant(val name: String, val address: String, val type: String, val notes: String):
  Serializable {
  override fun toString(): String {
    return name
  }
}

class RestaurantAdapter(val ctx: Context, val rests: ArrayList<Restaurant>): ArrayAdapter<Restaurant>(ctx, R.layout.list_row, rests) {
  override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
    val row = convertView ?: (ctx as AppCompatActivity).layoutInflater.inflate(R.layout.list_row, parent, false)
    val r = rests[position]
    row.findViewById<TextView>(R.id.title).text = r.name
    row.findViewById<TextView>(R.id.address).text = r.address
    val symbol = row.findViewById<ImageView>(R.id.symbol)
    when (r.type) {
      "sit" -> symbol.setImageResource(R.drawable.ball_red)
      "take" -> symbol.setImageResource(R.drawable.ball_yellow)
      "delivery" -> symbol.setImageResource(R.drawable.ball_green)
    }
    return row
  }
}