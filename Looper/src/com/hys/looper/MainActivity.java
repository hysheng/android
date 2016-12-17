package com.hys.looper;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {

	private Button send;
	private Handler handler;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send=(Button)findViewById(R.id.button1);
        new Thread(new ChildThread()).start();
        send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Message message=Message.obtain();
				message.arg1=1;
				message.arg2=2;
				message.what=3;
				message.obj="main thread";
				handler.sendMessage(message);
				
				
			}
		});
    }
    
    class ChildThread implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Looper.prepare();
			handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if(msg.what==3){
				Log.i("test", "message from Parent thread :"+msg.obj);
				}
			}	
			};
			Looper.loop();
			
		}
   
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
