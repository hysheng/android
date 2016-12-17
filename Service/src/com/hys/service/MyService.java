package com.hys.service;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {

	private static final String TAG = "test";
	private int i = 0;
	private Boolean state = true;
	private Random random=new Random();
	private MyBinder binder = new MyBinder();

	public class MyBinder extends Binder {
		public MyService getService() {
			return MyService.this;
		};

		@Override
		protected boolean onTransact(int code, Parcel data, Parcel reply,
				int flags) throws RemoteException {
			// TODO Auto-generated method stub
			Log.i(TAG,
					"从Activity获得的数据为：" + data.readInt() + "--->>"
							+ data.readString());
			reply.writeInt(getRandom());
			reply.writeString("yoona");
			return super.onTransact(code, data, reply, flags);
		}
	}

	public int getRandom() {
		return random.nextInt(20);
	}

	// 可以通过message来传递停止service的信息
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				stopSelf();
			}
		};
	};

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i(TAG, "服务被创建");
		Log.i(TAG, "----当前进程为：" + Thread.currentThread());
		new Thread() {

			@Override
			public void run() {
				Log.i(TAG, "当前进程为：" + Thread.currentThread());
				while (state) {

					Log.i(TAG, "i的值为：" + (i++));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Message message = Message.obtain();
					message.what = 1;
					handler.sendMessage(message);

				}
			}

		}.start();

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "服务被启动");
		return super.onStartCommand(intent, flags, startId);

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "服务被销毁");
		this.state = false;
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.i(TAG, "服务被绑定");
		return binder;
	}

}
