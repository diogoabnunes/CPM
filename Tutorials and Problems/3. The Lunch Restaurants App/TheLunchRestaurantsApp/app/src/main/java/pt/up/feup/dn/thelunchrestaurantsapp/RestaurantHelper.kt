package pt.up.feup.dn.thelunchrestaurantsapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val DATABASE_NAME = "lunchlist.db"
const val SCHEMA_VERSION = 1

class RestaurantHelper(val ctx: Context): SQLiteOpenHelper(ctx, DATABASE_NAME, null, SCHEMA_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE Restaurants(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, type TEXT, notes TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVer: Int, newVer: Int) {
        // no-op we use only version 1
    }

    fun getAll(): Cursor {
        return readableDatabase.rawQuery(
            "SELECT _id, name, address, type, notes FROM Restaurants ORDER BY name", null)
    }

    fun getById(id: String): Cursor {
        val args = arrayOf(id)
        return readableDatabase.rawQuery(
            "SELECT _id, name, address, type, notes FROM Restaurants WHERE _id=?", args)
    }

    fun insert(name: String, address: String, type: String, notes: String): Long {
        val cv = ContentValues()
        cv.put("name", name)
        cv.put("address", address)
        cv.put("type", type)
        cv.put("notes", notes)
        return writableDatabase.insert("Restaurants", "name", cv)
    }

    fun update(id: String, name: String, address: String, type: String, notes: String) {
        val cv = ContentValues()
        val args = arrayOf(id)
        cv.put("name", name)
        cv.put("address", address)
        cv.put("type", type)
        cv.put("notes", notes)
        writableDatabase.update("Restaurants", cv, "_id=?", args)
    }

    fun getName(c: Cursor): String {
        return c.getString(1)
    }

    fun getAddress(c: Cursor): String {
        return c.getString(2)
    }

    fun getType(c: Cursor): String {
        return c.getString(3)
    }

    fun getNotes(c: Cursor): String {
        return c.getString(4)
    }
}