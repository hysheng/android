package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Receiver2 extends BroadcastReceiver {
	private static final String TAG="test";

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String info=intent.getStringExtra("name");
		Log.i(TAG, "---Receiver2-->>>"+info);
		//只能在有序广播中调用此方法，否则会出错
		abortBroadcast();
	}

}
