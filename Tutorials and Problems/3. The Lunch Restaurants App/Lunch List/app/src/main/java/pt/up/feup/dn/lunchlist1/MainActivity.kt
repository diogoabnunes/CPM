package pt.up.feup.dn.lunchlist1

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {
    private val rests = arrayListOf<Restaurant>()
    private var current: Restaurant? = null
    private val adapter by lazy { RestaurantAdapter() }
    private val edName by lazy { findViewById<EditText>(R.id.edit_name) }
    private val edAddress by lazy { findViewById<EditText>(R.id.edit_address) }
    private val rgTypes by lazy { findViewById<RadioGroup>(R.id.rg_types) }
    private val edNotes by lazy { findViewById<EditText>(R.id.edit_notes) }

    private val tabs by lazy { findViewById<TabLayout>(R.id.tabs) }
    private val listTab by lazy { tabs.newTab().setText("List") }
    private val detailsTab by lazy { tabs.newTab().setText("Details") }
    private val tab1 by lazy { findViewById<ListView>(R.id.listView) }
    private val tab2 by lazy { findViewById<View>(R.id.rest_parameters) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setIcon(R.drawable.rest_icon)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_save).setOnClickListener { onButtonSaveClick() }

        // Tabs
        tabs.addTab(listTab)
        tabs.addTab(detailsTab)
        tabs.addOnTabSelectedListener(this)

        // ListView
        tab1.adapter = adapter
        tab1.setOnItemClickListener { _, _, pos, _ -> onRestItemClick(pos) }
    }

    // Main Menu Methods

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(this).inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.raise_toast) {
            val message = current?.notes ?: "No restaurant selected"
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    // Listener Functions

    private fun onButtonSaveClick() {
        val type = when(rgTypes.checkedRadioButtonId) {
            R.id.rb_take_out -> "take-out"
            R.id.rb_sit_down -> "sit-down"
            R.id.rb_delivery -> "delivery"
            else -> ""
        }
        val r = Restaurant(edName.text.toString(), edAddress.text.toString(), type, edNotes.text.toString())

        adapter.add(r)
        current = r
        clearKeyboard(this)
        listTab.select()
    }

    private fun onRestItemClick(pos: Int) {
        val r = rests[pos]
        edName.setText(r.name)
        edAddress.setText(r.address)
        when (r.type) {
            "take-out" -> rgTypes.check(R.id.rb_take_out)
            "sit-down" -> rgTypes.check(R.id.rb_sit_down)
            "delivery" -> rgTypes.check(R.id.rb_delivery)
        }
        edNotes.setText(r.notes)
        current = r
        detailsTab.select()
    }

    // Interface TabLayout.OnTabSelectedListened Methods

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when (tab?.position) {
            0 -> tab1.visibility = View.VISIBLE
            1 -> tab2.visibility = View.VISIBLE
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        when (tab?.position) {
            0 -> tab1.visibility = View.INVISIBLE
            1 -> tab2.visibility = View.INVISIBLE
        }
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    // Util Methods

    private fun clearKeyboard(act: Activity) {
        val view = act.findViewById<View>(android.R.id.content)
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    // Inner ListView Adapter

    inner class RestaurantAdapter: ArrayAdapter<Restaurant>(this@MainActivity, R.layout.row, rests) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val row = convertView ?: layoutInflater.inflate(R.layout.row, parent, false)
            val r = rests[position]
            row.findViewById<TextView>(R.id.title).text = r.name
            row.findViewById<TextView>(R.id.address).text = r.address
            val symbol = row.findViewById<ImageView>(R.id.symbol)
            when (r.type) {
                "sit-down" -> symbol.setImageResource(R.drawable.ball_red)
                "take-out" -> symbol.setImageResource(R.drawable.ball_yellow)
                "delivery" -> symbol.setImageResource(R.drawable.ball_green)
            }
            return row
        }
    }
}