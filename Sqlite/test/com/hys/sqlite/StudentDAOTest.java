package com.hys.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.hys.dao.StudentDAO;
import com.hys.domain.Student;

public class StudentDAOTest extends AndroidTestCase {

	private static final String TAG = "test";

	public void testAdd() {
		StudentDAO studentDAO = new StudentDAO(getContext(), "test.db", null, 1);
		Student student = new Student(2010110, "Hugh", 19);
		studentDAO.add(student);
		Log.i(TAG, "add successed");
	}

	public void testQuery() {
		StudentDAO studentDAO = new StudentDAO(getContext(), "test.db", null, 1);
		Student student = new Student();
		student = studentDAO.query(2010110);
		if (student != null) {
			Log.i(TAG, student.getId()+"--"+student.getName()+"--"+student.getAge());
		} else {
			Log.i(TAG, "Data no find");
		}

	}

	public void testUpdate() {
		StudentDAO studentDAO = new StudentDAO(getContext(), "test.db", null, 1);
		Student student = new Student(2010110, "Yoona", 18);
		studentDAO.add(student);
		Log.i(TAG, "update successed");
	}

	public void testDelete() {
		StudentDAO studentDAO = new StudentDAO(getContext(), "test.db", null, 1);
		studentDAO.delete(1, 2, 3);
		Log.i(TAG, "delete successed");
	}

	public void testGetCount() {
		StudentDAO studentDAO = new StudentDAO(getContext(), "test.db", null, 1);
		int count = studentDAO.getCount();
		Log.i(TAG, "" + count);
	}

	public void testGetStudents() {
		StudentDAO studentDAO = new StudentDAO(getContext(), "test.db", null, 1);
		List<Student> students = new ArrayList<Student>();
		students = studentDAO.getStudents(0, 1);
		for (Student student : students) {
			Log.i(TAG, student.toString());
		}

	}

}
