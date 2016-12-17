package com.hys.cache;

import android.app.Activity;

import com.hys.tools.BitMapTools;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MemoryCache extends Activity implements OnClickListener {

	private LruCache<String, Bitmap> mMemoryCache;
	private ImageView mImageView;
	private ImageView tImageView;
	private Button bt;
	private static int num = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mImageView = (ImageView) findViewById(R.id.mImageView);
		tImageView=(ImageView)findViewById(R.id.tImageView);
		bt=(Button)findViewById(R.id.bt);
		bt.setOnClickListener(this);
		final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
		final int cacheSize = maxMemory / 8;
		mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				return bitmap.getByteCount() / 1024;
			}
		};
		loadBitmap(R.drawable.star6, mImageView);

	}

	public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
		if (getBitmapFromMemCache(key) == null) {
			Log.i("test", "将图片写入内存缓存中");
			mMemoryCache.put(key, bitmap);
		}
	}

	public Bitmap getBitmapFromMemCache(String key) {
		
		Log.i("test", "从内存缓存中获取图片资源----" +(num++));
		return mMemoryCache.get(key);
	}

	public void loadBitmap(int resId, ImageView imageView) {
		final String imageKey = String.valueOf(resId);

		final Bitmap bitmap = getBitmapFromMemCache(imageKey);
		if (bitmap != null) {
			imageView.setImageBitmap(bitmap);
		} else {
			imageView.setImageResource(R.drawable.replace);
			BitmapWorkerTask task = new BitmapWorkerTask(imageView);
			task.execute(resId);
		}
	}

	class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
		private ImageView imageView;

		public BitmapWorkerTask(ImageView mImageView) {
			// TODO Auto-generated constructor stub
			this.imageView = mImageView;
		}

		@Override
		protected Bitmap doInBackground(Integer... params) {
			final Bitmap bitmap = BitMapTools.decodebBitmapFromResources(
					getResources(), params[0], 100, 100);
			addBitmapToMemoryCache(String.valueOf(params[0]), bitmap);
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			imageView.setImageBitmap(result);
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		loadBitmap(R.drawable.star6, tImageView);
		
	}

}
