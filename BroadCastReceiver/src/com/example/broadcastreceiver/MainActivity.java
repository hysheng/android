package com.example.broadcastreceiver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {

	private Button bt=null;
	private static final String ACTION="com.hys.action";
	protected static final String TAG = "BroadcastReceiver";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt=(Button)findViewById(R.id.bc);
        bt.setOnClickListener(listener);
    }
    private OnClickListener listener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
//			Intent intent=new Intent();
//			intent.setAction(ACTION);
//			Log.i(TAG, "广播已经发送");
//			sendBroadcast(intent);
//			Intent intent=new Intent(MainActivity.this, Receiver.class);
//			intent.putExtra("name", "Yoona love Hugh!");
//			sendBroadcast(intent);
			Intent intent=new Intent();
			intent.setAction(ACTION);
			intent.putExtra("name", "Yoona love Hugh!");
			sendOrderedBroadcast(intent, null);
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
