package com.hys.mylock;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class LockService extends Service {

	private static final String TAG="LockService";
	private Intent lockIntent;
	private KeyguardManager mKeyguardManager;
	private KeyguardLock mKeyguardLock;
	
	@Override
	public void onCreate() {
		lockIntent=new Intent(this,LockView.class);
		lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		IntentFilter screenOnFilter=new IntentFilter("android.intent.action.SCREEN_ON");
		this.registerReceiver(screenOnReceiver, screenOnFilter);
		
		IntentFilter screenOfFilter=new IntentFilter("android.intent.action.SCREEN_OFF");
		this.registerReceiver(screenOffReceiver, screenOfFilter);
		
		
		super.onCreate();
	}
	private BroadcastReceiver screenOffReceiver=new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			Log.v(TAG, "ÆÁÄ»Ëø¶¨");
			
		}
	};
	
	private BroadcastReceiver screenOnReceiver=new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			String action=intent.getAction();
			if(action.equals("android.intent.action.SCREEN_ON")||
					action.equals("android.intent.action.SCREEN_OFF")){
				mKeyguardManager=(KeyguardManager) getSystemService(KEYGUARD_SERVICE);
				mKeyguardLock=mKeyguardManager.newKeyguardLock("");
				mKeyguardLock.disableKeyguard();
				startActivity(lockIntent);
				
			}
			Log.v(TAG, "ÆÁÄ»½âËø");
			
		}
	};
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.unregisterReceiver(screenOnReceiver);
		this.unregisterReceiver(screenOffReceiver);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
