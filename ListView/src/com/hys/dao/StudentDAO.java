package com.hys.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class StudentDAO  {
	private static final String TAG = "SQLite";
	private DBOpenHelper helper;
	private SQLiteDatabase db;
	public StudentDAO(Context context,String name,CursorFactory factory,int version){
		helper=new DBOpenHelper(context, name, factory, version);
	}
		
	public Cursor getPersons(int start,int count){
		db=helper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select * from person limit ?,?",new String[]{String.valueOf(start),
				String.valueOf(count)});
		
		return cursor;
		
	}
}
