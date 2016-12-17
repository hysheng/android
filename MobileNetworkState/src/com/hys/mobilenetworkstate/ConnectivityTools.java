package com.hys.mobilenetworkstate;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivityTools {

	private static ConnectivityManager manager;

	public static boolean isCheckConnected(Context context) {
		boolean flag = false;
		manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo wifNetworkInfo = manager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		NetworkInfo mobileNetworkInfo = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if(wifNetworkInfo.isConnected()||mobileNetworkInfo.isConnected()){
			flag=true;
		}
		return flag;

	}
}
