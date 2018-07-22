package com.hzyc.zcm.homework3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by lenovo on 2018/7/15.
 */
public class OpenSqlite extends SQLiteOpenHelper {

    private static final int VERSION = 1; //数据库的版本
    private static final String DB_NAME = "data.db";

    public OpenSqlite(Context context) {
        super(context, DB_NAME, null,  VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        boolean bol = false;
        db.execSQL("create table addressList (id integer primary key autoincrement,  name varchar(10), phone int(20), image varchar(50))");
        bol = true;
        Log.i("数据库状态","Create"+bol);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
