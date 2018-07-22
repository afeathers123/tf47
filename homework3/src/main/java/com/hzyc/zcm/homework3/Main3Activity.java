package com.hzyc.zcm.homework3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    private Button selectBack,call,message;
    private TextView selectName, selectPhone;
    private Intent intent;
    private String phone = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        selectBack = (Button) findViewById(R.id.selectBack);
        call = (Button) findViewById(R.id.call);
        message = (Button) findViewById(R.id.message);
        selectName = (TextView) findViewById(R.id.selectName);
        selectPhone = (TextView) findViewById(R.id.selectPhone);

        intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("name");


        SQLiteDatabase sqLiteDatabase = new OpenSqlite(Main3Activity.this).getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from addressList where name = ?", new String[]{name});
        while(cursor.moveToNext()){
            name = cursor.getString(cursor.getColumnIndex("name"));
            phone = cursor.getString(cursor.getColumnIndex("phone"));
        }
        selectName.setText(name);
        selectPhone.setText(phone);

        selectBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Main3Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phone));
                if (ActivityCompat.checkSelfPermission(Main3Activity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto://"+phone));
                intent.putExtra("sms_body", "send detail");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("name");
        int id = item.getItemId();
        switch (id) {
            case R.id.updatePage:
                SQLiteDatabase sqLiteDatabase = new OpenSqlite(Main3Activity.this).getReadableDatabase();
                intent = new Intent(Main3Activity.this, Main4Activity.class);
                bundle = new Bundle();
                bundle.putString("name",name);

                intent.putExtras(bundle);

                startActivityForResult(intent,0);
                break;
            case R.id.deletePage:
                sqLiteDatabase = new OpenSqlite(Main3Activity.this).getReadableDatabase();
                sqLiteDatabase.execSQL("delete from addressList where name=?", new Object[]{name});
                intent = new Intent(Main3Activity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
