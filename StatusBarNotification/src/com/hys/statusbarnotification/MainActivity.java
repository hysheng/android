package com.hys.statusbarnotification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;


public class MainActivity extends Activity {
	private Button btnBar;
	private Button btnCancel;
	private Button btnUpdate;
	private Button btnCustom;
	private Button builder;
	private static final int STATUS_BAR_ID=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBar=(Button)findViewById(R.id.btnBar);
        btnCancel=(Button)findViewById(R.id.btnCancel);
        btnUpdate=(Button)findViewById(R.id.btnUpdate);
        btnCustom=(Button)findViewById(R.id.btnCustom);
        builder=(Button)findViewById(R.id.builder);
        btnBar.setOnClickListener(listener);
        btnCancel.setOnClickListener(listener);
        btnUpdate.setOnClickListener(listener);
        btnCustom.setOnClickListener(listener);
        builder.setOnClickListener(listener);
    }
    private OnClickListener listener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			NotificationManager notificationManager=(NotificationManager)
					getSystemService(Context.NOTIFICATION_SERVICE);
			Notification notification;
			CharSequence contentTitle;
			CharSequence contentText;
			Intent intent;
			PendingIntent pendingIntent;
			switch (v.getId()) {
			case R.id.btnBar:
			notification=new Notification();
			notification.icon=R.drawable.bar;
			notification.tickerText="�����ݴ����ʼ";
			notification.when=System.currentTimeMillis();
			contentTitle="֪ͨ";
			contentText="�����ݿγ������ˣ���ҿ��Ե�½��վ��ȥ�ۿ��ˡ�";
			intent=new Intent(MainActivity.this, StatusBarActivity.class);
			pendingIntent=PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
			notification.setLatestEventInfo(getApplicationContext(), contentTitle, contentText, pendingIntent);
			notificationManager.notify(STATUS_BAR_ID, notification);
				break;
			case R.id.btnCancel:
				notificationManager.cancel(STATUS_BAR_ID);
				break;
			case R.id.btnUpdate:
				notification=new Notification();
				notification.icon=R.drawable.bar;
				notification.tickerText="Second message";
				notification.when=System.currentTimeMillis();
				contentTitle="֪ͨ";
				contentText="Two unread messages";
				intent=new Intent(MainActivity.this,StatusBarActivity.class);
				pendingIntent=PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
				notification.setLatestEventInfo(getApplicationContext(), contentTitle, contentText, pendingIntent);
				notificationManager.notify(STATUS_BAR_ID, notification);
				break;
			case R.id.btnCustom:
				notification=new Notification();
				notification.icon=R.drawable.bar;
				notification.tickerText="Oject���㿪ʼ";
				notification.when=System.currentTimeMillis();
				notification.flags=notification.FLAG_AUTO_CANCEL;
				RemoteViews contenViews=new RemoteViews(getPackageName(), R.layout.custome_statusbar);
				contenViews.setImageViewResource(R.id.image, R.drawable.notification_image);
				contenViews.setTextViewText(R.id.text, "�Զ������");
				notification.contentView=contenViews;
				intent=new Intent(MainActivity.this, StatusBarActivity.class);
				pendingIntent=PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
				notification.contentIntent=pendingIntent;
				notificationManager.notify(STATUS_BAR_ID, notification);
				break;
				case R.id.builder:
					intent=new Intent(MainActivity.this,StatusBarActivity.class);
					pendingIntent=PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
					notification= new Notification.Builder(MainActivity.this)
					.setAutoCancel(true)
					.setTicker("new message")
					.setSmallIcon(R.drawable.notification_image)
					.setContentText("�ڸ����أ�")
					.setDefaults(Notification.DEFAULT_SOUND)
					.setWhen(System.currentTimeMillis())
					.setContentIntent(pendingIntent)
					.getNotification();
					notificationManager.notify(STATUS_BAR_ID, notification);
					break;
			default:
				break;
			}
			
		}
	};

}
