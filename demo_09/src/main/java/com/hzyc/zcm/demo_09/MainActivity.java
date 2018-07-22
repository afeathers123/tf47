package com.hzyc.zcm.demo_09;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    private EditText name,psd;
    private Button save,read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.name);
        psd = (EditText) findViewById(R.id.psd);
        save = (Button) findViewById(R.id.save);
        read = (Button) findViewById(R.id.read);

        //1.xml SharedPreferences
        //2.内外存储  存可以 取可以 维护起来不方便
        /*save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = name.getText().toString().trim();
                String psd1 = psd.getText().toString().trim();
                //创建文件
                //如果data文件不存在  会创建
                //data存在 获取
                SharedPreferences sharedPreferences = getSharedPreferences("data",0);
                //编辑权限
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name",name1);
                editor.putString("psd",psd1);
                //编辑
                //editor.remove()//删除

                boolean bol = editor.commit();
                Toast.makeText(MainActivity.this, ""+bol, Toast.LENGTH_SHORT).show();
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("data",0);
                String name = sharedPreferences.getString("name","nothing1");
                String psd = sharedPreferences.getString("psd","nothing2");

                Toast.makeText(MainActivity.this, name+"@@@@@@"+psd, Toast.LENGTH_SHORT).show();
            }
        });*/

        //IO留
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString().trim();
                String psd1 = psd.getText().toString().trim();

                FileOutputStream out = null;
                boolean bol = false;
                try {
                    out = openFileOutput("data.txt",0);
                    String str = name1+"@@@@@@"+psd1;
                    out.write(str.getBytes());
                    bol = true;

                    Toast.makeText(MainActivity.this, "保存状态="+bol, Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String a;
                FileInputStream input = null;
                ByteArrayOutputStream out = null;
                try {
                    input =  openFileInput("data.txt");
                    out = new ByteArrayOutputStream();
                    //读取文件，存入字节数组b，返回读取到的字符数，存入read,默认每次将b数组装满
                    byte[] b=new byte[1024];
                   *//* input.read(b);
                    a = new String(b);*//*
                    int len = 0;
                    while((len = input.read(b)) != -1){
                        out.write(b,0,len);
                    }

                    String values = out.toString();
                    String[] s = values.split("@@@@@@");
                    Toast.makeText(MainActivity.this, s[0], Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, s[1], Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        input.close();
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }*/
                String uri = "content://com.hzyc.zcm.demo_11.MyContentProvider/person";
                ContentResolver cr = getContentResolver();
                Cursor cursor = cr.query(Uri.parse(uri), new String[]{"name", "age"}, null, null, null);
                while (cursor.moveToNext()){
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    int age = cursor.getInt(cursor.getColumnIndex("age"));
                    Log.i("内容提供者信息","name="+name+"   age="+age);
                }
            }
        });

    }
}
