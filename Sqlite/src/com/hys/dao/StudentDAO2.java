package com.hys.dao;

import java.util.ArrayList;
import java.util.List;

import com.hys.domain.Student;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
/*
 * 用android提供的方法来操作数据库
 */
public class StudentDAO2 {
	
	private DBOpenHelper helper;
	private SQLiteDatabase db;
	private Cursor cursor;
	public StudentDAO2(Context context,String databaaeName,CursorFactory factory,
			int version){
		helper=new DBOpenHelper(context, databaaeName, factory,version);
		db=helper.getWritableDatabase();
	
	}
	
	
	
	public void add(Student student){
		ContentValues values=new ContentValues();
		values.put("id", student.getId());
		values.put("name", student.getName());
		values.put("age", student.getAge());
		db.insert("student","id", values);
	}
	public void update(Student student){

		ContentValues values=new ContentValues();
		values.put("name", student.getAge());
		values.put("age", student.getName());
		db.update("student", values, "id=?", new String[]{String.valueOf(student.getId())});
		
	}
	public Student query(int id){
		cursor=db.query("student", new String[]{"id","name","age"},
				"id=?", new String[]{String.valueOf(id)},
				null, null, null);
		if(cursor.moveToNext()){
			Student student=new Student();
			student.setId(cursor.getInt(cursor.getColumnIndex("id")));
			student.setName(cursor.getString(cursor.getColumnIndex("name")));
			student.setAge(cursor.getInt(cursor.getColumnIndex("id")));
			return student;
		}
		return null;
	}
	public void delete(Integer... ids){
		if(ids.length>0){
			StringBuffer sb=new StringBuffer();
			String[] sid=new String[ids.length];
			for(int i:ids){
				sb.append("?").append(",");
				sid[i]=String.valueOf(ids[i]);
			}
			sb.deleteCharAt(sb.length()-1);
			db.delete("student", "id in("+sb+")", sid);
		}
	}
	public int getCount(){
		int i=0;
		cursor=db.query("student", new String[]{"count(id)"}, 
				null, null, null, null, null);
		if(cursor.moveToNext()){
			i=cursor.getInt(0);
		}
		return i;
	}
	public List<Student> getStudents(int start,int count){
		List<Student> students=new ArrayList<Student>();
		Student student=new Student();
		cursor=db.query("student", new String[]{"id","name","age"},
				null, null, null, null, "id desc", start+","+count);
		while (cursor.moveToNext()) {
			student.setId(cursor.getInt(0));
			student.setName(cursor.getString(1));
			student.setAge(cursor.getInt(2));
			students.add(student);
		}
		return students;
	}
}