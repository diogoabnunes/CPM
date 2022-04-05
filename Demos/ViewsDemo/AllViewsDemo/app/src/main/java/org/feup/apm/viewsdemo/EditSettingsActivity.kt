package org.feup.apm.viewsdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceScreen

class EditSettingsActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Display the fragment as the activity all content.
    supportFragmentManager.beginTransaction().replace(android.R.id.content, PrefsFragment()).commit()
  }
}

class PrefsFragment : PreferenceFragmentCompat() {
  override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
    if (arguments != null)
      setPreferencesFromResource(R.xml.preferences, arguments?.getString("rootKey"))
    else
      setPreferencesFromResource(R.xml.preferences, rootKey)
  }

  override fun onNavigateToScreen(prScreen: PreferenceScreen) {
    val newPrefsFragment = PrefsFragment()
    newPrefsFragment.arguments = Bundle().also{ it.putString("rootKey", prScreen.key) }
    parentFragmentManager.beginTransaction().replace(id, newPrefsFragment).addToBackStack(null).commit()
  }
}