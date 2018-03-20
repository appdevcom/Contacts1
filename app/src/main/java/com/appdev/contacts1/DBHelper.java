package com.appdev.contacts1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by americo on 14/03/2018.
 */


public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context, "CONTACTS1.DB", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        db.execSQL("CREATE TABLE contacts ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(128)," +
                "phone VARCHAR(32)," +
                "email VARCHAR(255)" +
                ")");

        db.execSQL("INSERT INTO contacts (name, phone, email) VALUES ('One', '123', 'a@b.com')");
        db.execSQL("INSERT INTO contacts (name, phone, email) VALUES ('Two', '456', 'b@b.com')");
        db.execSQL("INSERT INTO contacts (name, phone, email) VALUES ('Three', '789', 'b@b.com')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
}

