package com.hys.transferdata;

import android.app.Application;

public class MyApp extends Application {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		name="Yoona";
	}
	
}
