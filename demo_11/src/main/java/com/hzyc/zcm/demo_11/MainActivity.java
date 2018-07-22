package com.hzyc.zcm.demo_11;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button createDatabase, update, query,readContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDatabase = (Button) findViewById(R.id.createDatabase);
        update = (Button) findViewById(R.id.update);
        query = (Button) findViewById(R.id.query);
        readContact = (Button) findViewById(R.id.readContact);

        final SQLiteDatabase sqLiteDatabase = new OpenSqlite(MainActivity.this).getReadableDatabase();


        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uri = "content://com.hzyc.zcm.demo_11.MyContentProvider/person";
                ContentResolver cr = getContentResolver();
                ContentValues contentValues = new ContentValues();
                contentValues.put("name","擎天柱");
                contentValues.put("age",200);
                Uri uri1 = cr.insert(Uri.parse(uri),contentValues);
                Log.i("内容提供者数据信息",""+uri1);

                /*SQLiteDatabase sqLiteDatabase = new OpenSqlite(MainActivity.this).getReadableDatabase();

                sqLiteDatabase.execSQL("insert into person (name,age) values ('张三',20)");
                sqLiteDatabase.execSQL("insert into person (name,age) values ('李四',30)");
                sqLiteDatabase.execSQL("insert into person (name,age) values ('王五',40)");*/

            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*List<Map<String,Object>> listData = select("select * from person where age = ?", new String[]{"20"});
                Log.i("结果", "" + listData);*/

                String uri = "content://com.hzyc.zcm.demo_11.MyContentProvider/person/10";
                ContentResolver cr = getContentResolver();
                Cursor cursor = cr.query(Uri.parse(uri), new String[]{"name", "age"}, null, null, "id asc");
                while (cursor.moveToNext()){
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    int age = cursor.getInt(cursor.getColumnIndex("age"));
                    Log.i("内容提供者信息","name="+name+"   age="+age);
                }
            }
        });

        readContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver cr = getContentResolver();
                Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI,new String[]{ContactsContract.Contacts._ID,ContactsContract.Contacts.DISPLAY_NAME},null,null,null);
                while (cursor.moveToNext()){
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    Log.i("内容提供者数据信息","id="+id+"   name="+name);
                }
            }
        });
    }

    public List<Map<String,Object>> select(String sql,String[] selectionArgs){
        List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
        SQLiteDatabase sqLiteDatabase = new OpenSqlite(MainActivity.this).getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while(cursor.moveToNext()){
            Map<String, Object> mapData = new HashMap<String, Object>();
            for(int i=0;i<cursor.getColumnCount();i++){
                String columnName = cursor.getColumnName(i);
                String columValue = cursor.getString(cursor.getColumnIndex(columnName));
                mapData.put(columnName, columValue);
            }
            listData.add(mapData);
        }
        return listData;
    }

}
