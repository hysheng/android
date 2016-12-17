package com.hys.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class Main2 extends Activity {
	
	private Fragment fragment1,fragment2;
	private FragmentManager manager;
	private FragmentTransaction transaction;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);
		manager=getFragmentManager();
		fragment1=new Fragment1();
		fragment2=new Fragment2();
		transaction=manager.beginTransaction();
		transaction.add(R.id.f1, fragment1, "fragment1");
		transaction.add(R.id.f2,fragment2,"fragment2");
		transaction.commit();
	}
}
