package com.hys.checknetwork;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	private ConnectivityManager cManager;
	private NotificationManager nManager;

	private BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			NetworkInfo wifiInfo = cManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			NetworkInfo mobileInfo = cManager
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if(!wifiInfo.isConnected()&&!mobileInfo.isConnected()){
				Notification.Builder builder=new Notification.Builder(MainActivity.this);
				builder.setContentTitle("Tips");
				builder.setContentText("Network has disconnected!");
				//16以下不能使用build方法
				builder.setSmallIcon(R.drawable.ic_launcher);
				nManager.notify(1001, builder.build());
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		cManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		IntentFilter filter = new IntentFilter();
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(receiver, filter);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(cManager!=null){
			unregisterReceiver(receiver);
		}
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
}
