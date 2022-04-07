package org.example.sudoku

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceActivity
import android.preference.PreferenceManager

class Prefs : PreferenceActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    addPreferencesFromResource(R.xml.settings)
  }

  companion object {
    // Option names and default values
    private const val OPT_MUSIC = "music"
    private const val OPT_MUSIC_DEF = true
    private const val OPT_HINTS = "hints"
    private const val OPT_HINTS_DEF = true

    /** Get the current value of the music option  */
    fun getMusic(context: Context?): Boolean {
      return PreferenceManager.getDefaultSharedPreferences(context)
        .getBoolean(OPT_MUSIC, OPT_MUSIC_DEF)
    }

    /** Get the current value of the hints option  */
    fun getHints(context: Context?): Boolean {
      return PreferenceManager.getDefaultSharedPreferences(context)
        .getBoolean(OPT_HINTS, OPT_HINTS_DEF)
    }
  }
}