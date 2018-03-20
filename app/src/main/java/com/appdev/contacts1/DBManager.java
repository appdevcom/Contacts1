package com.appdev.contacts1;

/**
 * Created by americo on 14/03/2018.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DBHelper dbHelper;

    //private Context context;

    private SQLiteDatabase database;

    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {database = dbHelper.getWritableDatabase(); }


    public void close() {
        dbHelper.close();
    }



    public Cursor fetch() {
        String[] columns = new String[] { "_id", "name", "phone", "email" };
        //String[] columns = new String[] { "name", "phone", "email"  };
        Cursor cursor = database.query("contacts", columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    public Cursor fetch_one(long id) {
        Cursor cursor = database.rawQuery("SELECT * FROM contacts WHERE _id = " + id, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void insert(String name, String phone, String email) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        database.insert("contacts", null, contentValues);
    }

    public int update(long id, String name, String phone, String email) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        int i = database.update("contacts", contentValues, "_id=" + id, null);
        return i;
    }

    public void delete(long id) {
        database.delete("contacts", "_id=" + id, null);
    }

}