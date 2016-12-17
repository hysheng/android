package com.hys.song.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context, String dbFile, CursorFactory factory,
			int version) {
		super(context, dbFile, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table user(" + "userid integer primary key,"
				+ "username varchar(8) not null," + "password varchar(16),"
				+ "realname varchar(8)," + "number integer(12),"
				+ "email varchar(16)," + "alipay varchar(16),"
				+ "shoppingtrolleyid integer)");
		db.execSQL("create table goods(" + "goodsid integer primary key,"
				+ "goodsname varchar(8),goodsprice integer(8),"
				+ "salevolume integer," + "sallerid integer,"
				+ "imagecache varchar(32))");
		db.execSQL("create table birthday("
				+ "birthdayid integer primary key ,"
				+ "contactname varchar(8)," + "email varchar(16),"
				+ "address varchar(16)," + "birthday date," + "age integer,"
				+ "userid integer ,"
				+ "foreign key(userid) references user(userid))");
		db.execSQL("create table goodscheck(orderid integer primary key,"
				+ "goodsprice integer," + "goodsid integer,"
				+ "goodsname varchar(8)," + "username varchar(8),"
				+ "sellername varchar(8)," + "date datetime,"
				+ "number integer(12))");
		db.execSQL("create table shoppingtrolley(shoppingtrolleyid integer,"
				+ "goodsid integer ,foreign key(goodsid) references goods(goodsid))");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("alter table user rename to user_tamp");

		db.execSQL("drop table user_tamp");
	}

}
