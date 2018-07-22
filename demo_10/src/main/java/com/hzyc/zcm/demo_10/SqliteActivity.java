package com.hzyc.zcm.demo_10;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SqliteActivity extends AppCompatActivity {

    private Button createDatabase, update, query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        createDatabase = (Button) findViewById(R.id.createDatabase);
        update = (Button) findViewById(R.id.update);
        query = (Button) findViewById(R.id.query);

        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1,使用自己的帮助类
                OpenSqlite os = new OpenSqlite(SqliteActivity.this);
                //2.触发整个数据库的状态
                SQLiteDatabase sqLiteDatabase = os.getReadableDatabase();//读
                //os.getWritableDatabase();//写
            }
        });
    }
}
