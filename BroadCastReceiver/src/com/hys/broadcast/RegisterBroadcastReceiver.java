package com.hys.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class RegisterBroadcastReceiver extends BroadcastReceiver {
	
	private static final String TAG = "BroadcastReceiver";

	public RegisterBroadcastReceiver() {
		super();
		Log.i(TAG, "�㲥�������Ѿ�����");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "�㲥�Ѿ�������,�㲥���������ڽ���Ϊ��"+Thread.currentThread().getId());

	}

}
