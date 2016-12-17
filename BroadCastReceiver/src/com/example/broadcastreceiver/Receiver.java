package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Receiver extends BroadcastReceiver {
	private static final String TAG="test";

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String info=intent.getStringExtra("name");
		Log.i(TAG, "---Receiver1-->>>"+info);
	}

}
