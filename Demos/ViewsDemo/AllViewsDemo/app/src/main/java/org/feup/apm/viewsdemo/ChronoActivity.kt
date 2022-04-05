package org.feup.apm.viewsdemo

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.DateFormat
import java.util.*

class ChronoActivity : BaseActivity() {
  private val dateAndTimeLabel by lazy { findViewById<TextView>(R.id.dateAndTime) }
  private val fmtDateAndTime = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, Locale.UK)
  private val dateAndTime = Calendar.getInstance()
  private val dateListener =
    OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
      dateAndTime[Calendar.YEAR] = year
      dateAndTime[Calendar.MONTH] = monthOfYear
      dateAndTime[Calendar.DAY_OF_MONTH] = dayOfMonth
      updateLabel()
    }
  private val timeListener =
    OnTimeSetListener { _, hourOfDay, minute ->
      dateAndTime[Calendar.HOUR_OF_DAY] = hourOfDay
      dateAndTime[Calendar.MINUTE] = minute
      dateAndTime[Calendar.SECOND] = 0
      updateLabel()
    }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_chrono)
    supportActionBar?.setTitle(R.string.act1_name)
    super.createDrawer()
    findViewById<Button>(R.id.dateBtn).setOnClickListener {
      DatePickerDialog(
        this,
        dateListener,
        dateAndTime[Calendar.YEAR],
        dateAndTime[Calendar.MONTH],
        dateAndTime[Calendar.DAY_OF_MONTH]
      ).show()
    }
    findViewById<Button>(R.id.timeBtn).setOnClickListener {
      TimePickerDialog(
        this,
        timeListener,
        dateAndTime[Calendar.HOUR_OF_DAY],
        dateAndTime[Calendar.MINUTE],
        true
      ).show()
    }
    updateLabel()
  }

  private fun updateLabel() {
    dateAndTimeLabel.text = fmtDateAndTime.format(dateAndTime.time)
  }
}