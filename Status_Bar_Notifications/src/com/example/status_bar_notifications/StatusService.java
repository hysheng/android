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
		Log.i(TAG, "��ʼ����");
		showNotification(false);
		try {
			Thread.sleep(10000);
			showNotification(true);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		Log.i(TAG, "�������");

	}

	private void showNotification(boolean b) {

		Notification notification = null;
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Intent intent = new Intent(this, MainActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);

		if (!b) {
			notification = new Notification(R.drawable.ic_launcher, "��ʼ����",
					System.currentTimeMillis());

			notification.setLatestEventInfo(this, "����", "��������", contentIntent);

		} else {

			notification = new Notification(R.drawable.ic_launcher, "�������",
					System.currentTimeMillis());

			notification.setLatestEventInfo(this, "����", "�������", contentIntent);
		}
		notification.defaults = Notification.DEFAULT_ALL;
		manager.notify(R.layout.activity_main, notification);

	}

}
