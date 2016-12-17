package com.hys.message_demo;

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
	private static final int IS_FINISH = 1;
	
	private Handler handler=new Handler(){
		 @Override
		 public void handleMessage(android.os.Message msg) {
			 if(msg.what==IS_FINISH){
				 Log.i("test", "message is"+msg.obj.toString());
			 }
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
				new Thread(new MyThread()).start();
				
			}
		});
    }
    
    private class MyThread implements Runnable{

		

		@Override
		public void run() {
//			Message message=Message.obtain();
//			message.arg1=1;
//			message.arg2=2;
//			message.what=IS_FINISH;
//			message.obj="Hello";
//			handler.sendMessage(message);
			
			Message message =Message.obtain(handler);
//			Message message=Message.obtain(handler, IS_FINISH);
//			Message message=Message.obtain(handler,IS_FINISH,"Object");
			message.arg1=1;
			message.arg2=2;
			message.what=IS_FINISH;
			message.obj="Hello";
			message.sendToTarget();
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
