package com.hys.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class MyAppWidget extends AppWidgetProvider {
	
	private static final String TAG = "test";
	private static final String ACTION = "com.hys.action";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "onReceive");
		if(intent.getAction().equals(ACTION)){
			RemoteViews views=new RemoteViews(context.getPackageName(), R.layout.second);
			views.setTextViewText(R.id.info, "hehehehehe");
			AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(context);
			ComponentName provider=new ComponentName(context, MyAppWidget.class);
			appWidgetManager.updateAppWidget(provider, views);
			Log.i(TAG, "ok");
		}
		super.onReceive(context, intent);
		
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		Log.i(TAG, "onUpdate");
		for(int i=0;i<appWidgetIds.length;i++){
			Intent intent=new Intent(context, Process.class);
			PendingIntent pendingIntent=PendingIntent.getActivity(context, 0, intent, 0);
			
			RemoteViews views=new RemoteViews(context.getPackageName(), R.layout.second);
			views.setOnClickPendingIntent(R.id.btn, pendingIntent);
			
			Intent intent2=new Intent();
			intent2.setAction(ACTION);
			PendingIntent pendingIntent2=PendingIntent.getBroadcast(context, 0, intent2, 0);
			views.setOnClickPendingIntent(R.id.myBroadcast, pendingIntent2);
			
			appWidgetManager.updateAppWidget(appWidgetIds[i], views);
			Log.i(TAG, "Length:"+appWidgetIds.length+";i="+i);
		}
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		Log.i(TAG, "onDeleted");
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onEnabled(Context context) {
		Log.i(TAG, "onEnabled");
		super.onEnabled(context);
	}

	@Override
	public void onDisabled(Context context) {
		Log.i(TAG, "onDisabled");
		super.onDisabled(context);
	}

}
