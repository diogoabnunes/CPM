package org.feup.apm.lunchlist4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RestaurantsHelper extends SQLiteOpenHelper {
  private static final String DATABASE_NAME = "lunchlist.db";
  private static final int SCHEMA_VERSION = 1;

  public RestaurantsHelper(Context context) {
    super(context, DATABASE_NAME, null, SCHEMA_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE Restaurants(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, type TEXT, notes TEXT)");
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
    // no-op; dealing with version 1 only
  }

  public long insert(String name, String address, String type, String notes) {
    ContentValues cv=new ContentValues();
    cv.put("name", name);
    cv.put("address", address);
    cv.put("type", type);
    cv.put("notes", notes);
    return getWritableDatabase().insert("Restaurants", "name", cv);
  }

  public void update(String id, String name, String address, String type, String notes) {
    ContentValues cv=new ContentValues();
    String[] args={id};
    cv.put("name", name);
    cv.put("address", address);
    cv.put("type", type);
    cv.put("notes", notes);
    getWritableDatabase().update("Restaurants", cv, "_id=?", args);
  }

  public Cursor getAll() {
    return(getReadableDatabase().rawQuery("SELECT _id, name, address, type, notes FROM Restaurants ORDER BY name", null));
  }

  public Cursor getById(String id) {
    String[] args={id};
    return(getReadableDatabase().rawQuery("SELECT _id, name, address, type, notes FROM Restaurants WHERE _id=?", args));
  }

  public String getName(Cursor c) {
    return(c.getString(1));
  }

  public String getAddress(Cursor c) {
    return(c.getString(2));
  }

  public String getType(Cursor c) {
    return(c.getString(3));
  }

  public String getNotes(Cursor c) {
    return(c.getString(4));
  }
}
