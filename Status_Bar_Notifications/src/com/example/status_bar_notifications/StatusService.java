package com.example.status_bar_notifications;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

public class StatusService extends IntentService {

	private static final String TAG = "Service";

	public StatusService() {
		super("StatusService");

	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i(TAG, "开始下载");
		showNotification(false);
		try {
			Thread.sleep(10000);
			showNotification(true);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		Log.i(TAG, "下载完成");

	}

	private void showNotification(boolean b) {

		Notification notification = null;
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Intent intent = new Intent(this, MainActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);

		if (!b) {
			notification = new Notification(R.drawable.ic_launcher, "开始下载",
					System.currentTimeMillis());

			notification.setLatestEventInfo(this, "下载", "正在下载", contentIntent);

		} else {

			notification = new Notification(R.drawable.ic_launcher, "下载完成",
					System.currentTimeMillis());

			notification.setLatestEventInfo(this, "下载", "下载完成", contentIntent);
		}
		notification.defaults = Notification.DEFAULT_ALL;
		manager.notify(R.layout.activity_main, notification);

	}

}
