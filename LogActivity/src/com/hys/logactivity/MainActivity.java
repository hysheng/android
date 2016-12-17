package com.hys.logactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	private Button bt1 = null;
	private Button bt2 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt1 = (Button) findViewById(R.id.secondBtn);
		bt2 = (Button) findViewById(R.id.thirdBtn);
		Log.i(TAG, "MainActivity-->onCreate");
		
		bt1.setOnClickListener(listener);
		bt2.setOnClickListener(listener);

	}

	private OnClickListener listener = new OnClickListener() {

		Intent intent = new Intent();

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.secondBtn:
				intent.setClass(MainActivity.this, Second.class);
				startActivity(intent);
				break;

			case R.id.thirdBtn:
				intent.setClass(MainActivity.this, Third.class);
				startActivity(intent);
				break;
			}
		}
	};

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(TAG, "MainActivity-->onStart");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i(TAG, "MainActivity-->onRestart");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(TAG, "MainActivity-->onResume");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(TAG, "MainActivity-->onPause");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(TAG, "MainActivity-->onStop");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "MainActivity-->onDestroy");
	}

}
