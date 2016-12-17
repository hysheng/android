package com.hys.contentprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {
	
	private Button add1;
	private Button add2;
	private Button query1;
	private Button query2;
	private Button delete1;
	private Button delete2;
	private Button update1;
	private Button update2;
	private final static int ONE=1;
	private final static int TWO=2;
	private String selection="";
	private String[] selectionArgs;
	private static final String TAG = "test";
	private ContentResolver resolver=null;
	private Uri uri=null;
	private ContentValues values=new ContentValues();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resolver=getContentResolver();
        add1=(Button)findViewById(R.id.add1);
        add2=(Button)findViewById(R.id.add2);
        query1=(Button)findViewById(R.id.query1);
        query2=(Button)findViewById(R.id.query2);
        update1=(Button)findViewById(R.id.update1);
        update2=(Button)findViewById(R.id.update2);
        delete1=(Button)findViewById(R.id.delete1);
        delete2=(Button)findViewById(R.id.delete2);
        add1.setOnClickListener(listener);
        add2.setOnClickListener(listener);
        query1.setOnClickListener(listener);
        query2.setOnClickListener(listener);
        update1.setOnClickListener(listener);
        update2.setOnClickListener(listener);
        delete1.setOnClickListener(listener);
        delete2.setOnClickListener(listener);
    }
    private OnClickListener listener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.add1:
				add(ONE);
				break;
			case R.id.add2:
				add(TWO);
				break;
			case R.id.query1:
				query(ONE);
				break;
			case R.id.query2:
				query(TWO);
				break;
			case R.id.delete1:
				delete(ONE);
				break;
			case R.id.delete2:
				delete(TWO);
				break;
			case R.id.update1:
				update(ONE);
				break;
			case R.id.update2:
				update(TWO);
				break;
			}
			
		}
	};
	
	protected void add(int type) {
		switch (type) {
		case ONE:
			uri=Uri.parse("content://com.hys.provider.studentprovider/student");
			values.put("name", "Yoona");
			values.put("age", "23");
			Log.i(TAG, resolver.insert(uri, values).toString());
			break;

		case TWO:
			uri=Uri.parse("content://com.hys.provider.studentprovider/student/1");
			values.put("name", "Jessica");
			values.put("age", "25");
			Log.i(TAG, resolver.insert(uri, values).toString());
			break;
		}
	}
	protected void query(int type){
		String[] projection=new String[]{"id","name","age"};
		String sortOrder="";
		Cursor cursor=null;
		switch (type) {
		case ONE:
			uri=Uri.parse("content://com.hys.provider.studentprovider/student");
			selection="id<?";
			selectionArgs=new String[]{"3"};
			cursor=resolver.query(uri, projection, selection, selectionArgs, sortOrder);
			while(cursor.moveToNext()){
				Log.i(TAG, "id="+cursor.getInt(0)+"name="+cursor.getString(1)+"age="+cursor.getInt(2));
			}
			break;

		case TWO:
			uri=Uri.parse("content://com.hys.provider.studentprovider/student/1");
			cursor=resolver.query(uri, projection, selection, selectionArgs, sortOrder);
			while(cursor.moveToNext()){
				Log.i(TAG, "id="+cursor.getInt(0)+"name="+cursor.getString(1)+"age="+cursor.getInt(2));
			}
			break;
		}
	}
	protected void update(int type){
		switch (type) {
		case ONE:
			uri=Uri.parse("content://com.hys.provider.studentprovider/student");
			values.put("name", "Yuri");
			values.put("age", "24");
			selection="id=?";
			selectionArgs=new String[]{"1"};
			resolver.update(uri, values, selection, selectionArgs);
			break;

		case TWO:
			uri=Uri.parse("content://com.hys.provider.studentprovider/student/1");
			values.put("name", "Krystal");
			values.put("age", "19");
			selection="name=?";
			selectionArgs=new String[]{"Yoona"};
			resolver.update(uri, values, selection, selectionArgs);
			break;
		}
	}
	protected void delete(int type){
		switch (type) {
		case ONE:
			uri=Uri.parse("content://com.hys.provider.studentprovider/student");
			selection="id in(?,?)";
			selectionArgs=new String[]{"1","2"};
			resolver.delete(uri, selection, selectionArgs);
			
			break;

		case TWO:
			uri=Uri.parse("content://com.hys.provider.studentprovider/student/1");
			selection=null;
			selectionArgs=null;
			resolver.delete(uri, selection, selectionArgs);
			break;
		}
	}

}
