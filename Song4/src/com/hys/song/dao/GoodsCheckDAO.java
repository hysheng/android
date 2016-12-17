package com.hys.song.dao;

import java.util.ArrayList;
import java.util.List;

import com.hys.song.model.GoodsCheck;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class GoodsCheckDAO {
	private DBOpenHelper dbHelper;
	private SQLiteDatabase db;
	private Cursor cursor;

	public GoodsCheckDAO(Context context, String dbFile, CursorFactory factory,
			int version) {
		dbHelper = new DBOpenHelper(context, dbFile, factory, version);
		db = dbHelper.getWritableDatabase();
	}

	public void addCheck(GoodsCheck check) {
		db.execSQL(
				"insert into goodscheck(orderid,goodsprice,goodsid,goodsname,username,sellername,number,date)" +
				" values(?,?,?,datetime('now'))",
				new Object[] { check.getOrderId(), check.getGoodsPrice(),check.getGoodsId(),
						check.getGoodsName(),check.getUserName(),check.getSellerName(),check.getNumber() });
	}

	public int deleteCheck(Integer... orderids) {
		if (orderids.length > 0) {
			StringBuffer buffer = new StringBuffer();
			String[] oIds = new String[orderids.length];
			for (int i : orderids) {
				buffer.append("?").append(",");
				oIds[i] = String.valueOf(orderids[i]);
			}
			buffer.deleteCharAt(buffer.length() - 1);
			return db.delete("goodscheck", "userid in(" + buffer + ")", oIds);
		}
		return 0;
	}

	public GoodsCheck queryCheck(int orderId) {
		GoodsCheck check = null;
		cursor = db.query("goodscheck", new String[] { "goodsprice", "goodsid",
				"goodsname", "username", "sellername", "date", "number" },
				"orderid =?", new String[] { String.valueOf(orderId) }, null,
				null, null);
		if (cursor.moveToNext()) {
			check = new GoodsCheck();
			check.setOrderId(orderId);
			check.setGoodsId(cursor.getInt(cursor.getColumnIndex("goodsid")));
			check.setGoodsPrice(cursor.getInt(cursor
					.getColumnIndex("goodsprice")));
			check.setGoodsName(cursor.getString(cursor
					.getColumnIndex("goodsname")));
			check.setUserName(cursor.getString(cursor
					.getColumnIndex("username")));
			check.setSellerName(cursor.getString(cursor
					.getColumnIndex("sellername")));
			check.setDate(cursor.getString(cursor.getColumnIndex("date")));
			check.setNumber(cursor.getInt(cursor.getColumnIndex("number")));

		}
		return check;
	}

	public List<GoodsCheck> queryAll() {
		List<GoodsCheck> list = new ArrayList<GoodsCheck>();
		cursor = db.query("goodscheck", new String[] { "orderid", "goodsprice",
				"goodsid", "goodsname", "username", "sellername", "number" },
				null, null, null, null, null);
		if (cursor.moveToNext()) {
			GoodsCheck check = new GoodsCheck();
			check.setOrderId(cursor.getInt(cursor.getColumnIndex("orderid")));
			check.setGoodsId(cursor.getInt(cursor.getColumnIndex("goodsid")));
			check.setGoodsPrice(cursor.getInt(cursor
					.getColumnIndex("goodsprice")));
			check.setGoodsName(cursor.getString(cursor
					.getColumnIndex("goodsname")));
			check.setUserName(cursor.getString(cursor
					.getColumnIndex("username")));
			check.setSellerName(cursor.getString(cursor
					.getColumnIndex("sellername")));
			check.setNumber(cursor.getInt(cursor.getColumnIndex("number")));
			list.add(check);

		}
		return list;
	}
}
