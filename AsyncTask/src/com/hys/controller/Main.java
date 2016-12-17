package com.hys.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Main extends Activity implements OnClickListener {

	private Button download;
	private ImageView imageView;
	private ProgressDialog dialog;
	private String img_url = "http://img4.duitang.com/uploads/item/201412/28/20141228142221_hm4St.thumb.700_0.jpeg";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		download = (Button) findViewById(R.id.dl);
		imageView = (ImageView) findViewById(R.id.img);
		dialog = new ProgressDialog(this);
		dialog.setTitle("Tip");
		dialog.setMessage("Downloading,Please wait...");
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		dialog.setCancelable(false);
		download.setOnClickListener(this);
	}

	public class MyAsyncTask extends AsyncTask<String, Integer, Bitmap> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog.show();
		}
		
		@Override
		protected Bitmap doInBackground(String... params) {
			Bitmap bitmap=null;
			ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
			InputStream inputStream=null;
			byte[] buffer=new byte[1024];
			int len,update_length=0;
			long file_length;
			int progress=0;
			HttpClient httpClient=new DefaultHttpClient();
			HttpGet httpGet=new HttpGet(params[0]);
			try {
				HttpResponse httpResponse=httpClient.execute(httpGet);
				if(httpResponse.getStatusLine().getStatusCode()==200){
					inputStream=httpResponse.getEntity().getContent();
					file_length=httpResponse.getEntity().getContentLength();
					while((len=inputStream.read(buffer))!=-1){
						update_length+=len;
						progress=(int)((update_length/(float)file_length)*100);
						publishProgress(progress);
						outputStream.write(buffer, 0, len);
					}
					byte[] result=outputStream.toByteArray();
					bitmap=BitmapFactory.decodeByteArray(result, 0, result.length);
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(inputStream!=null){
					try {
						inputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return bitmap;
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			imageView.setImageBitmap(result);
			dialog.dismiss();
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			dialog.setProgress(values[0]);
		}
		
		

	}

	@Override
	public void onClick(View v) {
		new MyAsyncTask().execute(img_url);

	}

}
