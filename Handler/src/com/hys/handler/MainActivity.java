package com.hys.handler;

import android.app.Activity;
import android.util.Log;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
	
	private Button send;
	private Handler handler=new Handler(){
		
		@Override
		public void handleMessage(android.os.Message msg) {
			Log.i("test","Message.what=="+msg.what+"Message.Object=="+msg.obj.toString());
		};
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send=(Button)findViewById(R.id.send);
        send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
//						handler.sendEmptyMessage(3);
//						handler.sendEmptyMessageAtTime(4,5000);
//						handler.sendEmptyMessageDelayed(5, 4000);
						Message message=handler.obtainMessage();
						message.what=6;
						message.obj="hander.obtainMessage()";
						message.sendToTarget();
					}
				}).start();
				
			}
		});
    }
     
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
