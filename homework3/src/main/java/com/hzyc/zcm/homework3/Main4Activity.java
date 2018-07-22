package com.hzyc.zcm.homework3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main4Activity extends AppCompatActivity {

    private Button updateBack,update;
    private EditText updateName, updatePhone;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        update = (Button) findViewById(R.id.update);
        updateBack = (Button) findViewById(R.id.updatback);
        updateName = (EditText) findViewById(R.id.updateName);
        updatePhone = (EditText) findViewById(R.id.updatePhone);

        intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("name");
        String phone = null;

        SQLiteDatabase sqLiteDatabase = new OpenSqlite(Main4Activity.this).getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from addressList where name = ?", new String[]{name});
        while(cursor.moveToNext()){
            name = cursor.getString(cursor.getColumnIndex("name"));
            phone = cursor.getString(cursor.getColumnIndex("phone"));
        }
        updateName.setText(name);
        updatePhone.setText(phone);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = getIntent();
                Bundle bundle = intent.getExtras();
                String name = bundle.getString("name");

                SQLiteDatabase sqLiteDatabase = new OpenSqlite(Main4Activity.this).getReadableDatabase();

                Cursor cursor = sqLiteDatabase.rawQuery("select * from addressList where name = ?", new String[]{name});

                String id = null;

                while(cursor.moveToNext()) {
                    id = cursor.getString(cursor.getColumnIndex("id"));
                }

                name = updateName.getText().toString().trim();
                String phone = updatePhone.getText().toString().trim();

                sqLiteDatabase.execSQL("update addressList set name=?, phone=? where id=?", new Object[]{name,phone,id});

                intent = new Intent(Main4Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        updateBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Main4Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
