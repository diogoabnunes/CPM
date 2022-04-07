package org.feup.apm.fragmentplanets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class DetailsFragment : Fragment() {
  var mIndex = 0;

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    mIndex = arguments?.getInt("index") ?: 0
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    if (container == null)
      return null
    // Don't tie this fragment to anything through the inflater.
    val v = inflater.inflate(R.layout.details, container, false)
    v.findViewById<TextView>(R.id.text1).text = Planets.PROPS[mIndex]
    v.findViewById<ImageView>(R.id.image1).setImageResource(Planets.IMAGES[mIndex])
    return v
  }

  companion object {
    @JvmStatic
    fun newInstance(index: Int) =
      DetailsFragment().apply {
        arguments = Bundle().apply {
          putInt("index", index)
        }
      }

    fun newInstance(bundle: Bundle): DetailsFragment {
      val index = bundle.getInt("index", 0)
      return newInstance(index)
    }
  }
}