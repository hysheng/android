package com.hys.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class Main3 extends Activity {
	
	private FragmentManager manager;
	private FragmentTransaction transaction;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main3);
		manager=getFragmentManager();
		transaction=manager.beginTransaction();
		CatalogFragment catalogFragment=new CatalogFragment();
		transaction.add(R.id.catalog, catalogFragment, "catalogFragment");
		transaction.commit();
	}
}
