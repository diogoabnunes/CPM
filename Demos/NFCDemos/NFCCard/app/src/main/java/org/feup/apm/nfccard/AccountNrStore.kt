package org.feup.apm.nfccard

import android.content.Context
import android.preference.PreferenceManager
import android.widget.TextView

object AccountNrStore {
  private const val PREF_ACCOUNT_NUMBER = "account_number"
  private const val DEFAULT_ACCOUNT_NUMBER = "00000000"
  private val accountLock = Any()
  private var account: String? = null

  fun setAccount(c: Context, tv: TextView, accNr: String) {
    synchronized(accountLock) {
      tv.setText(String.format(c.getString(R.string.tv_state_template), tv.text, accNr))
      PreferenceManager.getDefaultSharedPreferences(c).edit().putString(PREF_ACCOUNT_NUMBER, accNr).apply()
      account = accNr
    }
  }

  fun getAccount(c: Context?): String {
    synchronized(accountLock) {
      if (account == null)
        account = PreferenceManager.getDefaultSharedPreferences(c).getString(PREF_ACCOUNT_NUMBER, DEFAULT_ACCOUNT_NUMBER)
      return account!!
    }
  }
}