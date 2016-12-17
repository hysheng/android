package com.hys.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	private FragmentManager manager;
	private FragmentTransaction transaction;
	private final static String TAG = "test";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i(TAG, "---Activity-->>>>>onCreate");
		manager = getFragmentManager();
		transaction = manager.beginTransaction();
		//FragmentDemo fragment = new FragmentDemo();
		FragmentLifeCycle fragment=new FragmentLifeCycle();
		transaction.add(R.id.line, fragment);
		transaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		Log.i(TAG, "---Activity-->>>>>onStart");
		super.onStart();
		
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		Log.i(TAG, "---Activity-->>>>>onRestart");
		super.onRestart();
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		Log.i(TAG, "---Activity-->>>>>onPause");
		super.onPause();
		 
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		Log.i(TAG, "---Activity-->>>>>onStop");
		super.onStop();
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Log.i(TAG, "---Activity-->>>>>onDestroy");
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.i(TAG, "---Activity-->>>>>onResume");
		super.onResume();
	}
	}
