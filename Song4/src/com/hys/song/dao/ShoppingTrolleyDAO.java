package com.hys.song.dao;

import java.util.ArrayList;
import java.util.List;

import com.hys.song.model.ShoppingTrolley;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class ShoppingTrolleyDAO {
	private DBOpenHelper dbHelper;
	private SQLiteDatabase db;
	private Cursor cursor;

	public ShoppingTrolleyDAO(Context context, String dbFile,
			CursorFactory factory, int version) {
		dbHelper = new DBOpenHelper(context, dbFile, factory, version);
		db = dbHelper.getWritableDatabase();
	}

	public long addGoodsToShoppingTrolley(ShoppingTrolley shoppingTrolley) {
		ContentValues values = new ContentValues();
		values.put("shoppingtrolleyid", shoppingTrolley.getShoppingTrolleyId());
		values.put("goodsid", shoppingTrolley.getGoodsId());
		return db.insert("shoppingtrolley", "shoppingtrolleyid", values);
	}

	public int deleteGoodsInShoppingTrolley(Integer... goodsIds) {
		if (goodsIds.length > 0) {
			StringBuffer buffer = new StringBuffer();
			String[] gIds = new String[goodsIds.length];
			for (int i : goodsIds) {
				buffer.append("?").append(",");
				gIds[i] = String.valueOf(goodsIds[i]);
			}
			buffer.deleteCharAt(buffer.length() - 1);
			return db.delete("shoppingtrolley", "goodsid in(" + buffer + ")", gIds);
		}
		return 0;
	}

	public int deleteCleanShoppingTrolley(int shoppingtrolleyid) {
		return db.delete("shoppingtrolley", "shoppingtrolleyid=?",
				new String[] { String.valueOf(shoppingtrolleyid) });

	}

	public List<ShoppingTrolley> queryGoodsFromShoppingTrolley() {
		List<ShoppingTrolley> list = new ArrayList<ShoppingTrolley>();
		cursor = db.query("shoppingtrolley", new String[] { "shoppingtrolley",
				"goodsid" }, null, null, null, null, null);
		while (cursor.moveToNext()) {
			ShoppingTrolley shoppingTrolley = new ShoppingTrolley();
			shoppingTrolley.setShoppingTrolleyId(cursor.getInt(cursor
					.getColumnIndex("shoppingtrolleyid")));
			shoppingTrolley.setGoodsId(cursor.getInt(cursor
					.getColumnIndex("goodsid")));
			list.add(shoppingTrolley);
		}
		return list;
	}

}
