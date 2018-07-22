package com.hzyc.zcm.demo_10;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    private Button add;
    private EditText code1, name1, number1, price1;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        add = (Button) findViewById(R.id.add);
        code1 = (EditText) findViewById(R.id.code1);
        name1 = (EditText) findViewById(R.id.name1);
        number1 = (EditText) findViewById(R.id.number1);
        price1 = (EditText) findViewById(R.id.price1);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSqlite os = new OpenSqlite(Main2Activity.this);
                SQLiteDatabase sqLiteDatabase = os.getReadableDatabase();

                String code = code1.getText().toString().trim();
                String name = name1.getText().toString().trim();
                String number = number1.getText().toString().trim();
                String price = price1.getText().toString().trim();

                sqLiteDatabase.execSQL("insert into Goods (code,name,number,price) values (?,?,?,?)", new Object[]{code,name,number,price});

                intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
