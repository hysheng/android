package com.hys.sqlite;

import com.hys.dao.StudentDAO2;
import com.hys.domain.Student;

import android.test.AndroidTestCase;
import android.util.Log;

public class StudentDAO2Test extends AndroidTestCase {
	private static final String TAG = "test";
	private StudentDAO2 studentDAO2;
	public void testAdd() {
		studentDAO2=new StudentDAO2(this.getContext(), "testt.db", null, 2);
		Student student=new Student(199121,"hugh",20);
		studentDAO2.add(student);
		Log.i(TAG, "insert successed");
	}

	public void testUpdate() {
		studentDAO2=new StudentDAO2(this.getContext(), "testt.db", null, 1);
	}

	public void testQuery() {
		studentDAO2=new StudentDAO2(this.getContext(), "testt.db", null, 1);
		studentDAO2.query(199121);
		Log.i(TAG, "query successed");
	}

	public void testDelete() {
		studentDAO2=new StudentDAO2(this.getContext(), "testt.db", null, 1);
		studentDAO2.delete(199121);
		Log.i(TAG, "delete successed");
	}

	public void testGetCount() {
		studentDAO2=new StudentDAO2(this.getContext(), "testt.db", null, 1);
	}

	public void testGetStudents() {
		studentDAO2=new StudentDAO2(this.getContext(), "testt.db", null, 1);
	}

}
