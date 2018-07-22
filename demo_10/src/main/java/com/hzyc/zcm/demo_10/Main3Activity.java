package com.hzyc.zcm.demo_10;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity {

    private Button delete;
    private EditText code2;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        delete = (Button) findViewById(R.id.delete);
        code2 = (EditText) findViewById(R.id.code2);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSqlite os = new OpenSqlite(Main3Activity.this);
                SQLiteDatabase sqLiteDatabase = os.getReadableDatabase();

                String code = code2.getText().toString().trim();

                sqLiteDatabase.execSQL("delete from goods where code=?", new Object[]{code});

                intent = new Intent(Main3Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
