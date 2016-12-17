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
		
		db.execSQL("create table person(_id varchar(4) primary key,name varhcar(10),number varchar(20),sex varchar(2))");
		db.execSQL("insert into person (_id,name,number,sex) values('1701','Jessica','184586874','f')");
		db.execSQL("insert into person (_id,name,number,sex) values('1702','Yoona','12356885','f')");
		db.execSQL("insert into person (_id,name,number,sex) values('1703','Krystal','65234108','f')");
		db.execSQL("insert into person (_id,name,number,sex) values('1704','Mike','65001022','m')");
		db.execSQL("insert into person (_id,name,number,sex) values('1705','Jeson','120640245','m')");
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i(TAG, "数据库已经更新");
	
		

	}

}
