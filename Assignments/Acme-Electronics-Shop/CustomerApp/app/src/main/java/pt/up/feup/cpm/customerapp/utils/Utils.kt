package pt.up.feup.cpm.customerapp.utils

import java.text.SimpleDateFormat
import java.util.*

class Utils {
    fun Date.dateToString(format: String): String {
        val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
        return dateFormatter.format(this)
    }
}