package com.hzyc.zcm.homework3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button addPage;
    private ListView contactList;
    private Intent intent;
    private String name;

    private ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addPage = (Button) findViewById(R.id.addPage);
        contactList = (ListView) findViewById(R.id.contactList);

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, getList());
        contactList.setAdapter(arrayAdapter);

        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                name = parent.getItemAtPosition(position).toString();
                intent = new Intent(MainActivity.this, Main3Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",name);

                intent.putExtras(bundle);

                startActivityForResult(intent,0);
            }
        });

        contactList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this,view);
                popupMenu.getMenuInflater().inflate(R.menu.menu1,popupMenu.getMenu());
                popupMenu.show();
                name = parent.getItemAtPosition(position).toString();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.updatePage:
                                SQLiteDatabase sqLiteDatabase = new OpenSqlite(MainActivity.this).getReadableDatabase();
                                intent = new Intent(MainActivity.this, Main4Activity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("name",name);

                                intent.putExtras(bundle);

                                startActivityForResult(intent,0);
                                break;
                            case R.id.deletePage:
                                sqLiteDatabase = new OpenSqlite(MainActivity.this).getReadableDatabase();
                                sqLiteDatabase.execSQL("delete from addressList where name=?", new Object[]{name});
                                intent = new Intent(MainActivity.this, MainActivity.class);
                                startActivity(intent);
                                break;
                    }
                        return true;
                    }
                });
                return true;
            }
        });

        addPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    public List<String> getList() {
        String name = null;
        List<String> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = new OpenSqlite(MainActivity.this).getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from addressList", null);
        while(cursor.moveToNext()){
            name = cursor.getString(cursor.getColumnIndex("name"));
            list.add(name);
        }
        return list;
    }

}
