package com.hys.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBOpenHelper extends SQLiteOpenHelper {

	private static final String TAG = "test";

	public DBOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(TAG, "DBOpenHelper-->onCreate");
		db.execSQL("create table student(id integer primary key,name varchar(20),age integer)");
		db.execSQL("create table birthday("
				+ "birthdayid integer primary key ,"
				+ "contactname varchar(8)," + "email varchar(16),"
				+ "address varchar(16)," + "birthday date," + "age integer,"
				+ "userid integer ,"
				+ "foreign key(userid) references user(userid))");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i(TAG, "数据库已经更新");
		db.execSQL("alter table student rename to student_tamp");
		db.execSQL("create table student(id integer primary key,name varchar(20),"
				+ "age integer,sex varchar(2))");
		db.execSQL("insert into student(id,name,age,sex) select id,name,age,'F' from "
				+ "student_tamp");
		db.execSQL("drop table student_tamp");

	}

}
