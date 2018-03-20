package com.appdev.contacts1;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/**
 * Created by americo on 15/03/2018.
 */

public class ContactsCursorAdapter extends ResourceCursorAdapter {

    public ContactsCursorAdapter(Context context, int layout, Cursor cursor, int flags) {
        super(context, layout, cursor, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView name_tv = (TextView) view.findViewById(R.id.name_tv);
        name_tv.setText(cursor.getString(cursor.getColumnIndex("name")));

        TextView phone_tv = (TextView) view.findViewById(R.id.phone_tv);
        phone_tv.setText(cursor.getString(cursor.getColumnIndex("phone")));

        TextView email_tv = (TextView) view.findViewById(R.id.email_tv);
        email_tv.setText(cursor.getString(cursor.getColumnIndex("email")));
    }



}
