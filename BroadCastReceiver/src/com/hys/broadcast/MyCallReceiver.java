package com.hys.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyCallReceiver extends BroadcastReceiver {

	private static final String TAG = "BroadcastReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "信息广播已经处理");

	}

}
