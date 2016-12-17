package com.hys.service;

import com.hys.service.MyService.MyBinder;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private static final String TAG = "test";
	private Button start, destroy, onbind, unbind, getStatus, intentService,
			transact;
	private MyService.MyBinder binder;
	private MyService myService;
	private boolean flag = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "当前进程为：" + Thread.currentThread());
		setContentView(R.layout.activity_main);
		start = (Button) findViewById(R.id.start);
		destroy = (Button) findViewById(R.id.destroy);
		onbind = (Button) findViewById(R.id.bind);
		unbind = (Button) findViewById(R.id.unbind);
		getStatus = (Button) findViewById(R.id.getStatus);
		intentService = (Button) findViewById(R.id.intentService);
		transact=(Button)findViewById(R.id.transact);
		onbind.setOnClickListener(listener);
		unbind.setOnClickListener(listener);
		getStatus.setOnClickListener(listener);
		start.setOnClickListener(listener);
		destroy.setOnClickListener(listener);
		intentService.setOnClickListener(listener);
		transact.setOnClickListener(listener);

	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(MainActivity.this, MyService.class);
			switch (v.getId()) {
			case R.id.start:
				startService(intent);
				break;
			case R.id.destroy:
				stopService(intent);
				break;
			case R.id.bind:
				bindService(intent, conn, Service.BIND_AUTO_CREATE);
				break;
			case R.id.unbind:
				unbindService(conn);
				break;
			case R.id.getStatus:
				if (flag) {
					Log.i(TAG, "返回的随机数为：" + myService.getRandom());
				} else {
					Log.i(TAG, "服务未绑定!");
				}
				break;
			case R.id.intentService:
				intent = new Intent(MainActivity.this, MyIntentService.class);
				startService(intent);
			case R.id.transact:
				Parcel data = Parcel.obtain();
				data.writeInt(22);
				data.writeString("hugh");
				Parcel reply = Parcel.obtain();
				try {
					binder.transact(IBinder.LAST_CALL_TRANSACTION, data, reply,
							0);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.i(TAG, "从service中返回的数据为：" + reply.readInt() + "--->>"
						+ reply.readString());

			default:
				break;
			}

		}
	};
	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i(TAG, "服务解除成功");

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i(TAG, "服务连接成功");
			flag = true;
			Log.i(TAG,
					"ComponentName===" + name.getPackageName() + "@"
							+ name.getClassName());
			binder = (MyBinder) service;
			myService = binder.getService();

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
