package com.hys.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class RegisterBroadcastReceiver extends BroadcastReceiver {
	
	private static final String TAG = "BroadcastReceiver";

	public RegisterBroadcastReceiver() {
		super();
		Log.i(TAG, "广播接收器已经创建");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "广播已经被处理,广播接收者所在进程为："+Thread.currentThread().getId());

	}

}
