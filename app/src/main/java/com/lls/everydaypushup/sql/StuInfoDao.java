package com.lls.everydaypushup.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.lls.everydaypushup.Bean.StuInfoBean;

import java.util.ArrayList;

/**
 * 作者：我的孩子叫好帅 on 2018/8/2 13:35
 * Q Q：779594494
 * 邮箱：17600949389@163.com
 */
public class StuInfoDao {
    //数据库帮助类对象
    private SQLiteDbHelper mySqliteOpenHelper;

    //构造函数
    public StuInfoDao(Context context) {
        mySqliteOpenHelper = new SQLiteDbHelper(context);
    }

    /**
     * 添加数据到数据库
     * @param stubean
     * @return
     */

    public boolean add(StuInfoBean stubean) {
        //创建数据库
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();

        //方法一：执行sql语句
        //db.execSQL("insert into info(name,phone) values(?,?);",new Object[]{stubean.name,stubean.phone});
//Userid varchar(10),UserName varchar(20),phone varchar(11),
// createData varchar(100),RunTime varchar(100),RunType varchar(100)
        //方法二
        ContentValues values = new ContentValues();//用map封装的对象，存放值
        values.put("Userid", stubean.Userid);
        values.put("UserName", stubean.UserName);
        values.put("phone", stubean.phone);
        values.put("createData", stubean.createData);
        values.put("RunTime", stubean.RunTime);
        values.put("RunType", stubean.RunType);
        values.put("RunMun", stubean.RunMun);


     //table: 表名 , nullColumnHack：可以为空，标示添加一个空行, values:数据一行的值 , 返回值：代表添加这个新行的Id ，-1代表添加失败
        long result = db.insert("info", null, values);  //返回值是新增的行的id, 失败是-1
        //关闭数据库对象
        db.close();
        if (result != -1) {  //添加成功
            return true;
        } else {
            return false;
        }
    }
/**
 * 删除文件
 * */
    public int del(String fileName) {
        //创建数据库,调用getReadableDatabase方法,来初始化数据库的创建
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
        //执行sql语句
        //db.execSQL("delete from info where name=?;",new Object[]{name});

        //table ：表名, whereClause: 删除条件, whereArgs：条件的占位符的参数 ; 返回值：成功删除多少行
        int result = db.delete("info", "fileName=?", new String[]{fileName});

        //关闭数据库对象
        db.close();
        return result;
    }

//    public int update(StuInfoBean stubean) {
//        //创建数据库
//        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
//        //执行sql语句
//        //db.execSQL("update info set phone=? where name=?;", new Object[]{stubean.phone, stubean.name});
//
//        ContentValues values = new ContentValues();//是用map封装的对象，用来存放值
//        values.put("phone", stubean.phone);
//
//       //table:表名, values：更新的值, whereClause:更新的条件, whereArgs：更新条件的占位符的值,返回值：成功修改多少行
//
//        int result = db.update("info", values, "name=?", new String[]{stubean.name});//name=? where的条件
//        //关闭数据库对象
//        db.close();
//        return result;//返回成功的行数
//    }

    /***
     * 查询所有的文件
     * @param userId
     * @return
     */
    public ArrayList<StuInfoBean> query(String userId) {
        ArrayList<StuInfoBean> list = new ArrayList<StuInfoBean>();
        //创建数据库
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();

        //执行sql语句，返回一个cursor对象，游标对象
        //Cursor cursor = db.rawQuery("select _id,name,phone from info where name=?", new String[]{name});

     //table:表名, columns：查询的列名,如果null代表查询所有列； selection:查询条件, selectionArgs：条件占位符的参数值,
     //groupBy:按什么字段分组, having:分组的条件, orderBy:按什么字段排序
//Userid varchar(10),UserName varchar(20),phone varchar(11),
// createData varchar(100),RunTime varchar(100),RunType varchar(100)
        Cursor cursor = db.query("info", new String[]{"Userid", "UserName", "phone","createData","RunTime","RunType","RunMun"}, "Userid=?", new String[]{userId}, null, null,"_id desc");//null,null,null,null,null,null,

        if (cursor != null && cursor.getCount() > 0) {
            //循环遍历结果，获取每一行的 值
            while (cursor.moveToNext()) {
                StuInfoBean stubean = new StuInfoBean();
                String id = cursor.getString(0);
//                System.out.println("string===="+string);
//                stubean.id = cursor.getInt(1) + "";

                stubean.UserName = cursor.getString(1);
                stubean.phone = cursor.getString(2);
                stubean.createData = cursor.getString(3);
                stubean.RunTime = cursor.getString(4);
                stubean.RunType = cursor.getString(5);
                stubean.RunMun = cursor.getString(6);

//
                list.add(stubean);
            }
            cursor.close();//关闭结果集
        }
        //关闭数据库对象
        db.close();
        return list;
    }
}
