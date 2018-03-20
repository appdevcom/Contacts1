package com.appdev.contacts1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditContactActivity extends AppCompatActivity {

    private DBManager dbManager;
    long  id;
    EditText name_txt, phone_txt, email_txt;
    String name, phone, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        dbManager = MainActivity.dbManager;

        id=getIntent().getLongExtra("id", 0);

        TextView result_tv= (TextView) findViewById(R.id.result_tv);
        result_tv.setText("New contact ");

        name_txt = (EditText) findViewById(R.id.name_txt);
        phone_txt = (EditText) findViewById(R.id.phone_txt);
        email_txt = (EditText) findViewById(R.id.email_txt);

        if (id>0) {

            result_tv.setText("Editing contact " + id);

            Cursor cursor = dbManager.fetch_one(id);

            name_txt.setText(cursor.getString(cursor.getColumnIndex("name")));
            phone_txt.setText(cursor.getString(cursor.getColumnIndex("phone")));
            email_txt.setText(cursor.getString(cursor.getColumnIndex("email")));

            edit_buttons();
        }


    }

    public void back(View view){
        finish();
    }

    public void save(View view){
        name= name_txt.getText().toString();
        phone= phone_txt.getText().toString();
        email= email_txt.getText().toString();

        if (id>0){
            dbManager.update(id, name, phone, email );
        } else {
            dbManager.insert(name, phone, email );
        }

        MainActivity.listview_refresh();
        finish();
    }

    public void delete(View view){
        dbManager.delete(id);
        MainActivity.listview_refresh();
        finish();
    }

    /*extra*/
    public void phoneTo(View view){
        phone= phone_txt.getText().toString();


        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phone));



        startActivity(intent);
       // startActivity( new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + PhoneNumber)));
    }

    public void emailTo(View view){

        email= email_txt.getText().toString();

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto: " + email));
        startActivity(Intent.createChooser(emailIntent, "Feedback from Contacts App"));
    }


    private void edit_buttons(){
        Button delete_btn = (Button) findViewById(R.id.delete_btn);
        delete_btn.setVisibility(View.VISIBLE);
        Button phone_btn = (Button) findViewById(R.id.phone_btn);
        phone_btn.setVisibility(View.VISIBLE);
        Button email_btn = (Button) findViewById(R.id.email_btn);
        email_btn.setVisibility(View.VISIBLE);
    }

    private void msg(String txt){
        Toast.makeText(this, txt, Toast.LENGTH_LONG).show();
    }
}
