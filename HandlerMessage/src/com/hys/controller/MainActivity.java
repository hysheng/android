package com.hys.controller;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends Activity implements OnClickListener{
	
	private String img_url="http://cdn.duitang.com/uploads/item/201412/02/20141202223925_d2jzj.thumb.700_0.jpeg";
	private ImageView imageView;
	private Button download;
	private static final int IS_FINISH=1;
	private ProgressDialog dialog;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.image);
        download=(Button)findViewById(R.id.download);
        dialog=new ProgressDialog(this);
        dialog.setTitle("Tip");
        dialog.setMessage("Downloading...");
        download.setOnClickListener(this);
    }
	private Handler handler=new Handler(){
		
		@Override
		public void handleMessage(android.os.Message msg) {
			if(msg.what==IS_FINISH){
				byte[] data=(byte[]) msg.obj;
				Bitmap bitmap=BitmapFactory.decodeByteArray(data, 0, data.length);
				imageView.setImageBitmap(bitmap);
				dialog.dismiss();
			}
			
		};
	};
    @Override
    public void onClick(View v) {
    		new Thread(new MyThread()).start();
    		dialog.show();
    	
    }

    public class MyThread implements Runnable{

		@Override
		public void run() {
			HttpClient httpClient=new DefaultHttpClient();
			HttpGet httpGet=new HttpGet(img_url);
			try {
				HttpResponse httpResponse=httpClient.execute(httpGet);
				if(httpResponse.getStatusLine().getStatusCode()==200){
					byte[] data=EntityUtils.toByteArray(httpResponse.getEntity());
					Message message=Message.obtain();
					message.obj=data;
					message.what=IS_FINISH;
					handler.sendMessage(message);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
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
