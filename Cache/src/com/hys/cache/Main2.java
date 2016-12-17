package com.hys.cache;

import java.io.InputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.hys.tools.HttpTools;
import com.hys.tools.ImageDiskCachUtil;

public class Main2 extends Activity implements OnClickListener {
	private ImageView img, imgcache;
	private Button delCache, read;
	private ImageDiskCachUtil imageDiskCacheUtil;
	// private String imgUrl =
	// "http://172.18.5.42:8080/Upload/upload/star1.png";
	private String imgUrl = "http://img2.iqilu.com/download/images/12/03/08/67/1513522557-15.jpg";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		img = (ImageView) findViewById(R.id.img);
		imgcache = (ImageView) findViewById(R.id.imgcache);
		read = (Button) findViewById(R.id.read);
		delCache = (Button) findViewById(R.id.delCache);
		imageDiskCacheUtil = new ImageDiskCachUtil(this);
		read.setOnClickListener(this);
		delCache.setOnClickListener(this);
		if (imageDiskCacheUtil.readImageFromDiskCacheToMemory(imgUrl) == null) {
			Log.v("test", "没有，我下载");
			new DiskCacheTask().execute(imgUrl);
		} else {
			Log.v("test", "已经有，我读取");
			img.setImageBitmap(imageDiskCacheUtil
					.readImageFromDiskCacheToMemory(imgUrl));
		}
	}

	class DiskCacheTask extends AsyncTask<String, Void, Bitmap> {
		@Override
		protected Bitmap doInBackground(String... params) {
			String imgUrl = params[0];
			InputStream inputStream = null;
			Bitmap bitmap = null;
			try {
				inputStream = HttpTools.getImageInputStream(imgUrl);
				imageDiskCacheUtil.writeImageFromMemoryToDiskCache(imgUrl,
						inputStream);
				//inputstream不能被读两次，第一次读的时候就会把数据读走
				bitmap = imageDiskCacheUtil.readImageFromDiskCacheToMemory(imgUrl);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			if (result != null) {
				img.setImageBitmap(result);
			}
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		imageDiskCacheUtil.writeInJournal();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		imageDiskCacheUtil.closeDiskLruCache();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.read:
			Log.v("test", "点击读取缓存");
			imgcache.setImageBitmap(imageDiskCacheUtil
					.readImageFromDiskCacheToMemory(imgUrl));
			break;
		case R.id.delCache:
			imageDiskCacheUtil.delteDiskLruCache();
			Toast.makeText(Main2.this, "清除缓存", Toast.LENGTH_SHORT).show();
		default:
			break;
		}

	}
}
