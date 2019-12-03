package com.example.pineappleym.lite.Functions.WorldTime;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {
    //数据库版本号
    private static final int Version = 1;
    //数据库名
    private static final String DBname = "myDatabase";
    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version) {
        // 参数说明
        // context：上下文对象
        // name：数据库名称
        // param：一个可选的游标工厂（通常是 Null）
        // version：当前数据库的版本，值必须是整数并且是递增的状态
        super(context,DBname,null,Version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_city = "create table if not exists city(cid INTEGER PRIMARY KEY autoincrement,name TEXT,area TEXT)";
        db.execSQL(create_city);
        String create_all_city = "create table if not exists all_city(cid INTEGER PRIMARY KEY autoincrement,name TEXT,area TEXT)";
        db.execSQL(create_all_city);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}


