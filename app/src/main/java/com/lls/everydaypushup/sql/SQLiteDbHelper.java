package com.lls.everydaypushup.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 作者：我的孩子叫好帅 on 2018/8/2 13:35
 * Q Q：779594494
 * 邮箱：17600949389@163.com
 */
public class SQLiteDbHelper extends SQLiteOpenHelper {
public  static String  SQLNAME="info.db";
    public SQLiteDbHelper(Context context) {
        //factory用来创建cursor对象，默认是null
        //super(context, name, factory, version);
        super(context, SQLNAME, null, 1);
    }

    //第一次的创建的 时候被调用,
    @Override
    public void onCreate(SQLiteDatabase db) {
        //通过SQLiteDatabase执行创建一个sql语句    public String createData;
        //    public String RecordTime;
        //    public String LanguageType;
        db.execSQL("create table info(_id integer primary key autoincrement,Userid varchar(10),UserName varchar(20),phone varchar(11),createData varchar(100),RunTime varchar(100),RunType varchar(100),RunMun varchar(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
