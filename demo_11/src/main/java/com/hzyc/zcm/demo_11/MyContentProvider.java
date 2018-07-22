package com.hzyc.zcm.demo_11;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2018-07-19.
 * 开发010的数据 sqlite中的数据
 */
public class MyContentProvider extends ContentProvider {

    //创建了当前的CP的 uri访问地址集合 默认里面没有任何地址
    private static final UriMatcher URIMATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    //只要CP被注册 默认最先加载
    private OpenSqlite openSqlite;

    static {
        //使用的时候  content://com.hzyc.yy.demo_010.MyContentProvider/person   == 1 整个person表
        //使用的时候  content://com.hzyc.yy.demo_010.MyContentProvider/person/10   == 2 id == 10
        //使用的时候  content://com.hzyc.yy.demo_010.MyContentProvider/person/10/name   == 3 id == 10 name列
        URIMATCHER.addURI("com.hzyc.zcm.demo_11.MyContentProvider", "person", 1);
        URIMATCHER.addURI("com.hzyc.zcm.demo_11.MyContentProvider", "person/#", 2);
        URIMATCHER.addURI("com.hzyc.zcm.demo_11.MyContentProvider", "person/#/*", 3);
    }

    @Override
    public boolean onCreate() {
        openSqlite = new OpenSqlite(getContext());
        return true;
    }


    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase sqLiteDatabase = openSqlite.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("person",projection,selection,selectionArgs,null,null,sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    //知道你的URI 是到底是操作文件  或是 数据
    //区分你uri操作的数据是一行还是多行
    public String getType(Uri uri) {
        int resultCode = URIMATCHER.match(uri);
        switch (resultCode) {
            case 1:
                return "vnd.android.cursor.dir/";
            case 2:
                return "vnd.android.cursor.item";

        }

        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        //1.解析这个uri
        //使用的时候  content://com.hzyc.yy.demo_010.MyContentProvider/person
        //content://com.hzyc.yy.demo_010.MyContentProvider/person 10
        int resultCode = URIMATCHER.match(uri);
        switch (resultCode) {
            case 1:
                SQLiteDatabase sqLiteDatabase = openSqlite.getReadableDatabase();
                long i = sqLiteDatabase.insert("person", null, values);
                Uri newUri = ContentUris.withAppendedId(uri, i);
                return newUri;
            case 2:
                break;
            case 3:
                break;
        }

        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int resultCode = URIMATCHER.match(uri);
        switch (resultCode) {
            case 1: //删除整个表
               break;
            case 2:
                SQLiteDatabase sqLiteDatabase = openSqlite.getReadableDatabase();
                int i = sqLiteDatabase.delete("person",selection,selectionArgs);
                return i;
            case 3:
                break;
        }


        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int resultCode = URIMATCHER.match(uri);
        SQLiteDatabase sqLiteDatabase = openSqlite.getReadableDatabase();
        int i;
        switch (resultCode) {
            case 1: //删除整个表
                break;
            case 2:
                i = sqLiteDatabase.update("person",values,selection,selectionArgs);
                return i;
            case 3:
                i = sqLiteDatabase.update("person",values,selection,selectionArgs);
                return i;
        }
        return 0;
    }
}
