package com.hys.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {

	private static final String TAG = "BroadcastReceiver";

	
	
	public MyBroadcastReceiver() {
		Log.i(TAG, "�㲥�������Ѿ�����");
	}



	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "�㲥�Ѿ�����");

	}

}
