package com.hys.gallery;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MainActivity extends Activity {

	private Gallery gallery;
	private ImageAdapter imageAdapter;
	private int[] resid = { R.drawable.star1, R.drawable.star2,
			R.drawable.star3, R.drawable.star4, R.drawable.star5,
			R.drawable.star6 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageAdapter=new ImageAdapter(this);
		gallery=(Gallery)findViewById(R.id.mygallery);
		gallery.setAdapter(imageAdapter);
	}

	public class ImageAdapter extends BaseAdapter {

		private Context context;
		private int mGalleryItemBackground;

		public ImageAdapter(Context context) {
			this.context = context;
			TypedArray typedArray = obtainStyledAttributes(R.styleable.Gallery);
			mGalleryItemBackground = typedArray.getResourceId(
					R.styleable.Gallery_android_galleryItemBackground, 0);

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Integer.MAX_VALUE;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return resid[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ImageView imageView = new ImageView(context);
			imageView.setImageResource(resid[position % resid.length]);
			imageView.setScaleType(ScaleType.FIT_XY);
			imageView.setLayoutParams(new Gallery.LayoutParams(1000, 500));
			imageView.setBackgroundResource(mGalleryItemBackground);
			return imageView;
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
