package com.hys.song.dao;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.hys.song.model.Goods;

public class GoodsDAO {
	private DBOpenHelper dbHelper;
	private SQLiteDatabase db;
	private Cursor cursor;

	public GoodsDAO(Context context, String dbFile, CursorFactory factory,
			int version) {
		dbHelper = new DBOpenHelper(context, dbFile, factory, version);
		db = dbHelper.getWritableDatabase();
	}

	public long addListGoods(List<Goods> list) {
		long stateCode;
		for (Goods goods : list) {
			ContentValues values = new ContentValues();
			values.put("goodsid", goods.getGoodsId());
			values.put("goodsname", goods.getGoodsName());
			values.put("goodsprice", goods.getGoodsPrice());
			values.put("salevolume", goods.getSalevolume());
			values.put("sallerid", goods.getSallerId());
			values.put("imagecache", goods.getImageCache());
			if((stateCode=db.insert("goods", "goodsid", values))==-1)
			return stateCode;
		}
		return 1;
	}
	public long addGoods(Goods goods) {	
			ContentValues values = new ContentValues();
			values.put("goodsid", goods.getGoodsId());
			values.put("goodsname", goods.getGoodsName());
			values.put("goodsprice", goods.getGoodsPrice());
			values.put("salevolume", goods.getSalevolume());
			values.put("sallerid", goods.getSallerId());
			values.put("imagecache", goods.getImageCache());
			db.insert("goods", "goodsid", values);
		return 1;
	}

	public int deleteAllOfGoods() {
		return db.delete("goods", null, null);
	}

	public Goods queryGoods(int goodsId) {
		Goods goods = null;
		cursor = db.query("goods", new String[] { "goodsname", "goodsprice",
				"salevolume", "sallerid", "imagepath" }, "goodsid=?",
				new String[] { String.valueOf(goodsId) }, null, null, null);
		if (cursor.moveToNext()) {
			goods = new Goods();
			goods.setGoodsId(goodsId);
			goods.setGoodsName(cursor.getString(cursor
					.getColumnIndex("goodsname")));
			goods.setGoodsPrice(cursor.getInt(cursor
					.getColumnIndex("goodsprice")));
			goods.setSalevolume(cursor.getInt(cursor
					.getColumnIndex("salevolume")));
			goods.setSallerId(cursor.getInt(cursor.getColumnIndex("sallerid")));
			goods.setImageCache(cursor.getString(cursor
					.getColumnIndex("imagepath")));

		}
		return goods;
	}
}
