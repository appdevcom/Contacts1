package com.appdev.contacts1;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static public DBManager dbManager;
    private ListView records_lv;
    static private ContactsCursorAdapter adapter;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list1();
    }


    public void list1() {
        dbManager = new DBManager(this);
        dbManager.open();
        cursor = dbManager.fetch();

        records_lv = (ListView) findViewById(R.id.records_lv);
        adapter = new ContactsCursorAdapter(this, R.layout.listview_record, cursor, 0 );
        records_lv.setAdapter(adapter);

        records_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {

                Intent edit_intent = new Intent(getApplicationContext(), EditContactActivity.class);
                edit_intent.putExtra("id", viewId);
                startActivity(edit_intent);
            }
        });



    }

    public void newRecord(View view){
        Intent edit_intent = new Intent(getApplicationContext(), EditContactActivity.class);

        edit_intent.putExtra("id", 0);
        startActivity(edit_intent);
    }

    static public void listview_refresh(){
        adapter.changeCursor(dbManager.fetch());
        adapter.notifyDataSetChanged();
    }

    private void msg(String txt){
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
    }

}
