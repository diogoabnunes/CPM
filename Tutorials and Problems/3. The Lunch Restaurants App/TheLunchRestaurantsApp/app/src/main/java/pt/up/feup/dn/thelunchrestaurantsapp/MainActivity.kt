package pt.up.feup.dn.thelunchrestaurantsapp

import android.content.Context
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*

const val ID_EXTRA = "pt.up.feup.dn.thelunchrestaurantsapp.POS"
var currentId: Long = -1L

class MainActivity : AppCompatActivity() {
    val dbHelper by lazy { RestaurantHelper(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setIcon(R.drawable.rest_icon)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        setContentView(R.layout.activity_main)

        val restaurantsCursor = dbHelper.getAll()
        startManagingCursor(restaurantsCursor)

        val list = findViewById<ListView>(R.id.listview)
        list.adapter = RestaurantAdapter(restaurantsCursor)
        list.emptyView = findViewById(R.id.empty_list)
        list.setOnItemClickListener { _, _, _, id -> onRestItemClick(id) }
    }

    override fun onDestroy() {
        super.onDestroy()
        dbHelper.close()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        MenuInflater(this).inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toast -> {
                var message = "No restaurant selected"
                if (currentId != -1L) {
                    val c = dbHelper.getById(currentId.toString())
                    c.moveToNext()
                    message = String.format("%s:\n%s", dbHelper.getName(c), dbHelper.getNotes(c))
                    c.close()
                }
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                true
            }
            R.id.add -> {
                startActivity(Intent(this, DetailsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onRestItemClick(id: Long) {
        currentId = id
        startActivity(Intent(this, DetailsActivity::class.java).putExtra(ID_EXTRA, id.toString()))
    }

    inner class RestaurantAdapter(c: Cursor) : CursorAdapter(this@MainActivity, c, true) {
        override fun newView(ctx: Context, c: Cursor, parent: ViewGroup): View {
            val row: View = layoutInflater.inflate(R.layout.row, parent, false)
            row.findViewById<TextView>(R.id.title).text = dbHelper.getName(c)
            row.findViewById<TextView>(R.id.address).text = dbHelper.getAddress(c)
            val symbol = row.findViewById<ImageView>(R.id.symbol)
            when (dbHelper.getType(c)) {
                "sit" -> symbol.setImageResource(R.drawable.ball_red)
                "take" -> symbol.setImageResource(R.drawable.ball_yellow)
                "delivery" -> symbol.setImageResource(R.drawable.ball_green)
            }
            return row
        }

        override fun bindView(v: View, ctx: Context, c: Cursor) {}
    }
}
