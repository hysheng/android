package com.hys.listview;



import com.hys.dao.StudentDAO;

import android.database.Cursor;
import android.test.AndroidTestCase;
import android.util.Log;

public class StudentDAOTest extends AndroidTestCase {
	
	private final static String TAG="test";
	
	public void testGetPersons() {
		StudentDAO studentDAO=new StudentDAO(this.getContext(), "listview.db", null, 1);
		Cursor cursor=studentDAO.getPersons(1, 6);
		if (cursor.moveToNext()) {
			Log.i(TAG,cursor.getString(cursor.getColumnIndex("_id")));
			Log.i(TAG,cursor.getString(cursor.getColumnIndex("name")));
			Log.i(TAG,cursor.getString(cursor.getColumnIndex("number")));
			Log.i(TAG,cursor.getString(cursor.getColumnIndex("sex")));
			
	}
	}
}
