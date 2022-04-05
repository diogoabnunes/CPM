package org.feup.apm.fragmentplanets

import android.content.Context
import androidx.fragment.app.ListFragment
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView

class TitlesFragment : ListFragment() {
  private var myActivity: MainActivity? = null
  private var mCurCheckPosition = -1

  override fun onAttach(ctx: Context) {
    super.onAttach(ctx)
    myActivity = ctx as MainActivity
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (savedInstanceState != null) {
      mCurCheckPosition = savedInstanceState.getInt("curChoice", 0)
    }
    // Define the list adapter with our static array of titles.
    listAdapter = ArrayAdapter<String>(myActivity as Context, android.R.layout.simple_list_item_1, Planets.PLANETS)
  }

  /* configure the list view after it was created */
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    listView.choiceMode = ListView.CHOICE_MODE_SINGLE
    if (mCurCheckPosition != -1) {
      listView.setSelection(mCurCheckPosition)
      myActivity!!.showDetails(mCurCheckPosition)
    }
  }

  override fun onSaveInstanceState(saveBundle: Bundle) {
    saveBundle.putInt("curChoice", mCurCheckPosition)
    super.onSaveInstanceState(saveBundle)
  }

  override fun onListItemClick(l: ListView, v: View, pos: Int, id: Long) {
    l.setSelection(pos)
    mCurCheckPosition = pos
    myActivity!!.showDetails(pos)
  }

  override fun onDetach() {
    super.onDetach()
    myActivity = null
  }
}