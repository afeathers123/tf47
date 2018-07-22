package com.hzyc.zcm.demo_10;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {

    private Button update;
    private EditText name3, number3, price3;
    private TextView code3;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        update = (Button) findViewById(R.id.update);
        code3 = (TextView) findViewById(R.id.code3);
        name3 = (EditText) findViewById(R.id.name3);
        number3 = (EditText) findViewById(R.id.number3);
        price3 = (EditText) findViewById(R.id.price3);

        String na3=null,c3=null,nu3=null,p3=null;
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        String code = bundle.getString("code");
        Log.i("名字","Code"+code);

        OpenSqlite os = new OpenSqlite(Main4Activity.this);
        SQLiteDatabase sqLiteDatabase = os.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from goods where code = ?", new String[]{code});
        while(cursor.moveToNext()){
            c3 = cursor.getString(cursor.getColumnIndex("code"));
            na3 = cursor.getString(cursor.getColumnIndex("name"));
            nu3 = cursor.getString(cursor.getColumnIndex("number"));
            p3 = cursor.getString(cursor.getColumnIndex("price"));
            Log.i("名字","Create"+na3);
        }
        code3.setText(c3);
        name3.setText(na3);
        number3.setText(nu3);
        price3.setText(p3);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSqlite os = new OpenSqlite(Main4Activity.this);
                SQLiteDatabase sqLiteDatabase = os.getReadableDatabase();

                String code = code3.getText().toString().trim();
                String name = name3.getText().toString().trim();
                String number = number3.getText().toString().trim();
                String price = price3.getText().toString().trim();

                sqLiteDatabase.execSQL("update goods set name=?, number=?, price=? where code=?", new Object[]{name,number,price,code});

                intent = new Intent(Main4Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
