package com.hys.song.dao;

import java.util.ArrayList;
import java.util.List;

import com.hys.song.model.Birthday;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;



public class BirthdayDAO {
	private DBOpenHelper dbHelper;
	private SQLiteDatabase db;
	private Cursor cursor;

	public BirthdayDAO(Context context, String dbFile, CursorFactory factory,
			int version) {
		dbHelper = new DBOpenHelper(context, dbFile, factory, version);
		db = dbHelper.getWritableDatabase();
	}

	public long addBirthday(Birthday birthday) {
		ContentValues values = new ContentValues();
		values.put("contactname", birthday.getContactName());
		values.put("email", birthday.getEmail());
		values.put("address", birthday.getAddress());
		values.put("birthday",birthday.getBirthday() );
		values.put("age", birthday.getAge());
		values.put("userid", birthday.getUserId());
		return db.insert("birthday", "contactname", values);
	}

	public int deleteBirthday(Integer... birthdayIds) {
		if (birthdayIds.length > 0) {
			StringBuffer buffer = new StringBuffer();
			String[] bIds = new String[birthdayIds.length];
			for (int i : birthdayIds) {
				buffer.append("?").append(",");
				bIds[i] = String.valueOf(birthdayIds[i]);
			}
			buffer.deleteCharAt(buffer.length() - 1);
			return db.delete("birthday", "birthdayid in(" + buffer + ")", bIds);
		}
		return 0;
	}

	public int updateBirthday(Birthday birthday) {
		ContentValues values = new ContentValues();
		values.put("contactname", birthday.getContactName());
		values.put("email", birthday.getEmail());
		values.put("address", birthday.getAddress());
		values.put("birthday",birthday.getBirthday() );
		values.put("age", birthday.getAge());
		values.put("userid", birthday.getUserId());
		return db.update("birthday", values, "birthdayid in=?",
				new String[] { String.valueOf(birthday.getBirthdayId()) });
	}

	public Birthday queryBirthday(int birthdayId) {
		Birthday birthday = null;
		cursor = db.query("birthday", new String[] { "contactname", "email",
				"address", "age", "birthday" }, "birthdayid=?",
				new String[] { String.valueOf(birthdayId) }, null, null, null);
		if(cursor.moveToNext()){
			birthday=new Birthday();
			birthday.setBirthdayId(birthdayId);
			birthday.setContactName(cursor.getString(cursor.getColumnIndex("contactname")));
			birthday.setEmail(cursor.getString(cursor.getColumnIndex("email")));
			birthday.setAddress(cursor.getString(cursor.getColumnIndex("address")));
			birthday.setBirthday(cursor.getString(cursor.getColumnIndex("birthday")));
			birthday.setAge(cursor.getInt(cursor.getColumnIndex("age")));
			
		}
		return birthday;
	}
	public List<Birthday> queryAll(){
		List<Birthday> list=new ArrayList<Birthday>();
		cursor = db.query("birthday", new String[] { "contactname", "email",
				"address", "age", "birthday" }, null,
				null, null, null, null);
		while(cursor.moveToNext()){
			Birthday birthday=new Birthday();
			birthday.setContactName(cursor.getString(cursor.getColumnIndex("contactname")));
			birthday.setEmail(cursor.getString(cursor.getColumnIndex("email")));
			birthday.setAddress(cursor.getString(cursor.getColumnIndex("address")));
			birthday.setBirthday(cursor.getString(cursor.getColumnIndex("birthday")));
			birthday.setAge(cursor.getInt(cursor.getColumnIndex("age")));
			list.add(birthday);			
		}
		return list;
	}
}
