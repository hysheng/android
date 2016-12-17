package com.hys.provider;

import com.hys.dao.DBOpenHelper;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

public class StudentContentProvider extends ContentProvider {
	private DBOpenHelper dbOpenHelper;
	private final static int STUDENTS=1;
	private final static int STUDENT=2;
	private long pid=0;
	private String whereString;
	private String[] params;
	private int flag;
	private SQLiteDatabase sqLiteDatabase;
	private final static String AUTHORITY="com.hys.provider.studentprovider";
	private static final UriMatcher sMatcher=new UriMatcher(UriMatcher.NO_MATCH);
	static{
		sMatcher.addURI(AUTHORITY, "student", STUDENTS);
		sMatcher.addURI(AUTHORITY, "student/#", STUDENT);
	}

	@Override
	public boolean onCreate() {
	dbOpenHelper=new DBOpenHelper(this.getContext(),"testProvider",null,1);
	return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		sqLiteDatabase=dbOpenHelper.getReadableDatabase();
		switch (sMatcher.match(uri)) {
		case STUDENTS:
			return sqLiteDatabase.query("student", projection, selection, selectionArgs, null, null, sortOrder);
		case STUDENT:
			pid=ContentUris.parseId(uri);
			whereString=TextUtils.isEmpty(selection)?"id£½£¿":selection+"and id=?";
			params=new String[]{String.valueOf(pid)};
			if(!TextUtils.isEmpty(selection)&&selectionArgs!=null){
				params=new String[selectionArgs.length+1];
				for(int i=0;i<selectionArgs.length;i++){
					params[i]=selectionArgs[i];
				}
				params[selectionArgs.length+1]=String.valueOf(pid);
			}
			return sqLiteDatabase.query("student", projection, whereString, params, null, null, sortOrder);
			default:
				throw new IllegalArgumentException("Unknow Uri:"+uri);
		}
	}

	@Override
	public String getType(Uri uri) {
		switch (sMatcher.match(uri)) {
		case STUDENTS:
			return "vnd.android.cursor.dir/personprovider.person";
		case STUDENT:
			return "vnd.android.cursor.item/personprovider.person";
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		sqLiteDatabase=dbOpenHelper.getReadableDatabase();
		switch (sMatcher.match(uri)) {
		case STUDENTS:
			pid=sqLiteDatabase.insert("student", "name", values);
			return ContentUris.withAppendedId(uri, pid);
		case STUDENT:
			pid=sqLiteDatabase.insert("student", "name", values);
			String path=uri.toString();
			return Uri.parse(path.substring(0, path.lastIndexOf('/')+1)+pid);
		default:
			throw new IllegalArgumentException("Unknow Uri:"+uri);
		}
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		sqLiteDatabase=dbOpenHelper.getReadableDatabase();
		switch (sMatcher.match(uri)) {
		case STUDENTS:
			flag=sqLiteDatabase.delete("student", selection, selectionArgs);
			break;
		case STUDENT:
			pid=ContentUris.parseId(uri);
			whereString=TextUtils.isEmpty(selection)?"id=?":selection+"and id=?";
			params=new String[]{String.valueOf(pid)};
			if(!TextUtils.isEmpty(selection)&&selectionArgs!=null){
			params=new String[selectionArgs.length+1];
			for(int i=0;i<selectionArgs.length;i++){
				params[i]=selectionArgs[i];
			}
			params[selectionArgs.length+1]=String.valueOf(pid);
			}
			flag=sqLiteDatabase.delete("student", whereString, params);
			break;
		default:
			throw new IllegalArgumentException("Unknow Uri:"+uri);
		}
		return flag;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		sqLiteDatabase=dbOpenHelper.getReadableDatabase();
		switch (sMatcher.match(uri)) {
		case STUDENTS:
			flag=sqLiteDatabase.update("student", values, selection, selectionArgs);
			break;
		case STUDENT:
			pid=ContentUris.parseId(uri);
			whereString=TextUtils.isEmpty(selection)?"id=?":selection+"and id=?";
			params=new String[]{String.valueOf(pid)};
			if(!TextUtils.isEmpty(selection)&&selectionArgs!=null){
				params=new String[selectionArgs.length+1];
				for(int i=0;i<selectionArgs.length;i++){
					params[i]=selectionArgs[i];
				}
				params[selectionArgs.length+1]=String.valueOf(pid);
			}
			flag=sqLiteDatabase.update("student", values, whereString, params);
			break;
		default:
			throw new IllegalArgumentException("Unkown Uri:"+uri);
		}
		return flag;
	}

}
