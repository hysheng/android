package com.hys.fileio;

import java.io.InputStream;
import java.io.OutputStream;

import com.hys.service.FileService;

import android.content.Context;
import android.test.AndroidTestCase;
import android.util.Log;

public class FileServiceTest extends AndroidTestCase {
	
	private static final String TAG = "test";

	public void testSave() {
		Log.i(TAG, "saving");
		FileService service=new FileService();
		try {
			OutputStream outputStream=this.getContext().openFileOutput("my.txt", Context.MODE_PRIVATE);
			service.save(outputStream, "hello world");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void testRead() {
		Log.i(TAG, "reading");
		FileService service=new FileService();
		String demo;
		try {
			InputStream inputStream=this.getContext().openFileInput("my.txt");
			demo=service.read(inputStream);
			Log.i(TAG, demo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
