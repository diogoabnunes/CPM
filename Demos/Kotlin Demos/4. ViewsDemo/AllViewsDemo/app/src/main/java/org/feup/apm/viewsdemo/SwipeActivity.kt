package org.feup.apm.viewsdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

const val ARG_OBJECT = "object"

class SwipeActivity : BaseActivity() {
  /**
   * The PagerAdapter provides fragments representing
   * each object in a collection. We use a FragmentStateAdapter
   * derivative, which will destroy and re-create fragments as needed, saving and restoring their
   * state in the process. This is important to conserve memory and is a best practice when
   * allowing navigation between objects in a potentially large collection.
   */

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_swipe)
    supportActionBar?.setTitle(R.string.act12_name)
    super.createDrawer()

    val mPagerAdapter = MyPagerAdapter(this)
    val mViewPager = findViewById<ViewPager2>(R.id.pager)
    val mTabLayout = findViewById<TabLayout>(R.id.tl_swipe)

    // Set up the ViewPager, attaching the adapter, also attaching a top TabLayout
    mViewPager.adapter = mPagerAdapter
    TabLayoutMediator(mTabLayout, mViewPager) { tab, pos -> tab.text = (mViewPager.adapter as MyPagerAdapter).getPageTitle(pos)}.attach()

    findViewById<Button>(R.id.bt_first).setOnClickListener { mViewPager.setCurrentItem(0,true) }
    findViewById<Button>(R.id.bt_last).setOnClickListener { mViewPager.currentItem = mPagerAdapter.itemCount - 1 }
  }
}

class MyPagerAdapter(act: FragmentActivity) : FragmentStateAdapter(act) {
  override fun createFragment(i: Int): Fragment {
    val fragment = MyFragment()
    val args = Bundle()
    args.putInt(ARG_OBJECT, i + 1)             // Our object is just an integer

    fragment.arguments = args
    return fragment
  }

  // For this example, we have a 100-object collection.
  override fun getItemCount(): Int = 100

  fun getPageTitle(position: Int): String {
    return "page " + (position + 1)
  }
}

class MyFragment : Fragment() {
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    val rootView: View = inflater.inflate(R.layout.fragment_collection_object, container, false)
    rootView.findViewById<TextView>(android.R.id.text1).text = arguments?.getInt(ARG_OBJECT).toString()
    return rootView
  }
}

