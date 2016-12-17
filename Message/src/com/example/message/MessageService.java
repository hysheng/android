package com.example.message;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class MessageService extends Service {

	private static final String TAG = "MainActivity";
	private static final int FLAGS = 1;
	private static final int MOON = 2;
	ServiceHandler handler=null;
	
	private class ServiceHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MOON:
				Log.i(TAG, "handlerMessage--->"+msg.obj);
				break;
			case FLAGS:
				Log.i(TAG, "handlerMessage--->"+msg.obj);
				break;	
			default:
				break;
			}
			stopSelf(msg.arg1);
		}

		public ServiceHandler(Looper looper){
			super(looper);
		}
		
	}

	@Override
	public void onCreate() {
		Log.i(TAG, "Message-->oncreate()");
		Looper looper=Looper.getMainLooper();
		handler=new ServiceHandler(looper);
		
		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "Message-->onStartCommand");
		Message message=new Message();
		message.what=FLAGS;
		message.arg1=startId;
		message.obj="Hugh";
		handler.sendMessage(message);
		return START_STICKY;
		
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i(TAG, "Message-->onDestroy");
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
