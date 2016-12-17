package com.example.broadcastreceiver;

import com.hys.broadcast.RegisterBroadcastReceiver;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Register extends Activity {
	protected static final String ACTION = "com.hys.action";
	private static final String TAG = "BroadcastReceiver";
	private Button send = null;
	private Button register = null;
	private Button unregister = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.mybroadcast);
		send = (Button) findViewById(R.id.SendBroadcast);
		register = (Button) findViewById(R.id.RegisterBroadcast);
		unregister = (Button) findViewById(R.id.UnregisterBroadcast);
		send.setOnClickListener(listener);
		register.setOnClickListener(listener);
		unregister.setOnClickListener(listener);
		Log.i(TAG, "	UI进程为："+Thread.currentThread().getId());

	}

	private OnClickListener listener = new OnClickListener() {

		
		RegisterBroadcastReceiver receiver=null;
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.SendBroadcast:
				Log.i(TAG, "-------SendBroadcast");
				Intent intent=new Intent();
				intent.setAction(ACTION);
				sendBroadcast(intent);
				break;
			case R.id.RegisterBroadcast:
				Log.i(TAG, "---------RegisterBroadcast");
				receiver=new RegisterBroadcastReceiver();
				IntentFilter filter=new IntentFilter();
				filter.addAction(ACTION);
				//运用开发一般将该方法放到activity生命周期的onResume()方法中
				registerReceiver(receiver, filter);
				break;
			case R.id.UnregisterBroadcast:
				Log.i(TAG, "---------UnregisterBroadcast");
				//运用开发一般将该方法放到activity生命周期的onPause()方法中
				unregisterReceiver(receiver);
				break;
			default:
				break;
			}

		}
	};

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
