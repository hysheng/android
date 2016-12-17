package com.hys.cache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.hys.tools.DiskCaCheTools;

import libcore.io.DiskLruCache;
import libcore.io.DiskLruCache.Snapshot;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity implements OnClickListener{
	private ImageView img;
	private DiskLruCache mDiskLruCache;
	private DiskCaCheTools dcTools;
	private File cacheDir;
	private String key;
	private Snapshot snapshot;
	private Button getSize;
	private Button delCache;
	private TextView size;
//	private String imgUrl = "http://192.168.24.110:8080/Upload/upload/star1.png";
	private String imgUrl = "http://img2.iqilu.com/download/images/12/03/08/67/1513522557-15.jpg";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		img = (ImageView) findViewById(R.id.img);
//		getSize=(Button)findViewById(R.id.getSize);
		delCache=(Button)findViewById(R.id.delCache);
//		getSize.setOnClickListener(this);
		delCache.setOnClickListener(this);
		size=(TextView)findViewById(R.id.size);
		dcTools = new DiskCaCheTools();
		try {
			 cacheDir = dcTools.getDiskCacheDir(this, "bitmap");
			if (!cacheDir.exists()) {
				cacheDir.mkdirs();
			}
			mDiskLruCache = DiskLruCache.open(cacheDir, 1, 1, 10 * 1024 * 1024);
			key = DiskCaCheTools.hashKeyForDisk(imgUrl);
			snapshot = mDiskLruCache.get(key);
			if (snapshot == null) {
				Log.v("test", "没有，我下载");
				new DiskCacheTask().execute(imgUrl);
			} else {
				InputStream inputStream = snapshot.getInputStream(0);
				Log.v("test", "已经有，我读取");
				img.setImageBitmap(BitmapFactory.decodeStream(inputStream));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	class DiskCacheTask extends AsyncTask<String, Void, Bitmap> {
		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			Bitmap bitmap = null;
			DiskLruCache.Snapshot snapshot;
			String imgUrl;
			String key = "";
			try {
				imgUrl = params[0];
				key = DiskCaCheTools.hashKeyForDisk(imgUrl);
				DiskLruCache.Editor editor = mDiskLruCache.edit(key);
				if (editor != null) {
					OutputStream outputStream = editor.newOutputStream(0);
					if (dcTools.downloadUrlToStream(imgUrl, outputStream)) {
						editor.commit();
					} else {
						editor.abort();
					}
				}
//				mDiskLruCache.flush();
				snapshot = mDiskLruCache.get(key);
				if (snapshot != null) {
					InputStream inputStream = snapshot.getInputStream(0);
					bitmap = BitmapFactory.decodeStream(inputStream);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			img.setImageBitmap(result);
		}
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(!mDiskLruCache.isClosed()){
			try {
				//将内存中的操作纪录同步到日志文件journal
				mDiskLruCache.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(!mDiskLruCache.isClosed()){
			try {
				mDiskLruCache.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
//		case R.id.getSize:
//			size.append(mDiskLruCache.size()/1024+"KB");
//			break;
		case R.id.delCache:
			try {
				mDiskLruCache.delete();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Toast.makeText(Main.this, "清除缓存", Toast.LENGTH_SHORT).show();
		default:
			break;
		}
		
	}
}
