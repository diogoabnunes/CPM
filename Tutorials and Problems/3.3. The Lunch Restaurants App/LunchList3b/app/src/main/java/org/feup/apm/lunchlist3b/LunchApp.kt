package org.feup.apm.lunchlist3b

import android.app.Application

class LunchApp: Application() {
  val rests: ArrayList<Restaurant> = ArrayList()
  var current: Restaurant? = null
  var adapter: RestaurantAdapter? = null
}