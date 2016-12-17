package com.hys.dao;

import java.util.ArrayList;
import java.util.List;

import com.hys.domain.Student;

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
	
	public void add(Student student){
		Log.i(TAG, "DBOpenHelper-->addStudent");
		db=helper.getWritableDatabase();
		db.execSQL("insert into student(id,name,age) values(?,?,?)",new Object[]{student.getId(),
				student.getName(),student.getAge()});
		}
	public Student query(int id){
		Log.i(TAG, "DBOpenHelper-->queryStudent");
		db=helper.getWritableDatabase();
		Cursor cursor=db.rawQuery("select id,name,age from student where id=?",
				new String[]{String.valueOf(id)});
		if (cursor.moveToNext()) {
			Student student=new Student();
			student.setId(cursor.getInt(cursor.getColumnIndex("id")));
			student.setName(cursor.getString(cursor.getColumnIndex("name")));
			student.setAge(cursor.getInt(cursor.getColumnIndex("age")));
			return student;
		}
		return null;
	}
	public void update(Student student){
		db=helper.getWritableDatabase();
		db.execSQL("update student set name=?,age=? where id=?",new Object[]{student.getName(),
				student.getAge(),student.getId()});
	}
	public void delete(Integer... sids){
		db=helper.getWritableDatabase();
		if (sids.length>0) {
			StringBuffer sb=new StringBuffer();
			for (int j = 0; j < sids.length; j++) {
				sb.append("?").append(",");
			}
			sb.deleteCharAt(sb.length()-1);
			db.execSQL("delete from student where id in("+sb+")",sids);
		}
	}
	public int getCount(){
		db=helper.getWritableDatabase();
		Cursor cursor=db.rawQuery("select count(id) from student", null);
		if(cursor.moveToNext()){
			return cursor.getInt(0);
		}
		return 0;
	}
	public List<Student> getStudents(int start,int count){
		List<Student> students=new ArrayList<Student>();
		Student student=new Student();
		Cursor cursor=db.rawQuery("select * from student limit ?,?",new String[]{String.valueOf(start),
				String.valueOf(count)});
		while(cursor.moveToNext()){
			student.setId(cursor.getInt(cursor.getColumnIndex("id")));
			student.setName(cursor.getString(cursor.getColumnIndex("name")));
			student.setAge(cursor.getInt(cursor.getColumnIndex("age")));
			students.add(student);
		}
		return students;
	}
}
