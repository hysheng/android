package com.hys.song.dao;

import com.hys.song.model.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class UserDAO {
	private DBOpenHelper dbHelper;
	private SQLiteDatabase db;
	private Cursor cursor;

	public UserDAO(Context context, String dbFile, CursorFactory factory,
			int version) {
		dbHelper = new DBOpenHelper(context, dbFile, factory, version);
		db = dbHelper.getWritableDatabase();

	}

	public long addUser(User user) {

		ContentValues values = new ContentValues();
		values.put("userid", user.getUserId());
		values.put("username", user.getUserName());
		values.put("password", user.getPassword());
		values.put("realname", user.getRealName());
		values.put("number", user.getNumber());
		values.put("email", user.getEmail());
		values.put("alipay", user.getAliPay());
		values.put("shoppingtrolleyid", user.getShoppingTrolleyId());
		return db.insert("user", "userid", values);

	}

	public int deleteUser(Integer... userIds) {
		if (userIds.length > 0) {
			StringBuffer buffer = new StringBuffer();
			String[] uIds = new String[userIds.length];
			for (int i : userIds) {
				buffer.append("?").append(",");
				uIds[i] = String.valueOf(userIds[i]);
			}
			buffer.deleteCharAt(buffer.length() - 1);
			return db.delete("user", "userid in(" + buffer + ")", uIds);
		}
		return 0;
	}

	public int updateUser(User user) {
		ContentValues values = new ContentValues();
		values.put("username", user.getUserName());
		values.put("password", user.getPassword());
		values.put("realname", user.getRealName());
		values.put("number", user.getNumber());
		values.put("email", user.getEmail());
		values.put("alipay", user.getAliPay());
		return db.update("user", values, "userid in=?",
				new String[] { String.valueOf(user.getUserId()) });
	}

	public User queryUser(int userId) {
		User user = null;
		cursor = db.query("user", new String[] { "username", "realname",
				"number", "email", "alipay", "shoppingtrolleyid" },
				"userid =?", new String[] { String.valueOf(userId) }, null,
				null, null);
		if (cursor.moveToNext()) {
			user = new User();
			user.setUserId(userId);
			user.setUserName(cursor.getString(cursor.getColumnIndex("username")));
			user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
			user.setRealName(cursor.getString(cursor.getColumnIndex("realname")));
			user.setNumber(cursor.getInt(cursor.getColumnIndex("number")));
			user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
			user.setAliPay(cursor.getString(cursor.getColumnIndex("alipay")));
			user.setShoppingTrolleyId(cursor.getInt(cursor
					.getColumnIndex("shoppingtrolleyid")));

		}
		return user;
	}
}
