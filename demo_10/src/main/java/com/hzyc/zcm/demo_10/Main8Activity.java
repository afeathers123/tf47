package com.hzyc.zcm.demo_10;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main8Activity extends AppCompatActivity {

    private Button back;
    private TextView code4, name4, number4, price4;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        back = (Button) findViewById(R.id.back);
        code4 = (TextView) findViewById(R.id.code4);
        name4 = (TextView) findViewById(R.id.name4);
        number4 = (TextView) findViewById(R.id.number4);
        price4 = (TextView) findViewById(R.id.price4);

        String na4=null,c4=null,nu4=null,p4=null;
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        String searchNum = bundle.getString("searchNum");
        String searchText = null;

        if("1".equals(searchNum)){
            OpenSqlite os = new OpenSqlite(Main8Activity.this);
            SQLiteDatabase sqLiteDatabase = os.getReadableDatabase();
            searchText = bundle.getString("searchText");
            Cursor cursor = sqLiteDatabase.rawQuery("select * from goods where code = ?", new String[]{searchText});
            while(cursor.moveToNext()){
                c4 = cursor.getString(cursor.getColumnIndex("code"));
                na4 = cursor.getString(cursor.getColumnIndex("name"));
                nu4 = cursor.getString(cursor.getColumnIndex("number"));
                p4 = cursor.getString(cursor.getColumnIndex("price"));
            }
            code4.setText(c4);
            name4.setText(na4);
            number4.setText(nu4);
            price4.setText(p4);
        }else if("2".equals(searchNum)){
            OpenSqlite os = new OpenSqlite(Main8Activity.this);
            SQLiteDatabase sqLiteDatabase = os.getReadableDatabase();
            searchText = bundle.getString("searchText");
            Cursor cursor = sqLiteDatabase.rawQuery("select * from goods where name = ?", new String[]{searchText});
            while(cursor.moveToNext()){
                c4 = cursor.getString(cursor.getColumnIndex("code"));
                na4 = cursor.getString(cursor.getColumnIndex("name"));
                nu4 = cursor.getString(cursor.getColumnIndex("number"));
                p4 = cursor.getString(cursor.getColumnIndex("price"));
            }
            code4.setText(c4);
            name4.setText(na4);
            number4.setText(nu4);
            price4.setText(p4);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Main8Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
