package com.hys.bitmap;

import java.lang.ref.SoftReference;

import com.hys.tools.BitMapTools;
import com.hys.tools.HttpUtils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class BitMapLoadAll extends Activity {
	
	private ListView listView;
	private String[] imgs=Images.imageUrls;
	private ImageAdapter adapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loadall);
		listView=(ListView)findViewById(R.id.list);
		adapter=new ImageAdapter();
		listView.setAdapter(adapter);
		adapter.notifyDataSetChanged();		
	}
	class ImageAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imgs.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return imgs[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view=null;
			if(convertView==null){
				view=LayoutInflater.from(BitMapLoadAll.this).inflate(R.layout.item, null);
			}else {
				view=convertView;
			}
			ImageView imageView=(ImageView)view.findViewById(R.id.img);
			loadBitmap(imgs[position], imageView);
			return view;
		}
		
	}
	/*
	 * ListView结合异步任务操作时，有可能出现重用的布局不能被及时回收，导致内存吃紧
	 * 可以使用软引用
	 * 在滑动这些ListView的时候，会对旧的布局进行资源回收
	 */
	static class AsyncDrawable extends BitmapDrawable{
		private final SoftReference<BitmapWorkerTask> softReference;
		public AsyncDrawable(Resources resources,Bitmap bitmap,BitmapWorkerTask bitmapWorkerTask){
			super(resources,bitmap);
			softReference=new SoftReference<BitMapLoadAll.BitmapWorkerTask>(bitmapWorkerTask);
		}
		public BitmapWorkerTask getBitmapWorkerTask(){
			return softReference.get();
		}
	}
	class BitmapWorkerTask extends AsyncTask<String , Void, Bitmap>{
		
		private SoftReference<ImageView> imgsReference;
		private String data="";
		
		public BitmapWorkerTask(ImageView imageView){
			this.imgsReference=new SoftReference<ImageView>(imageView);
		}
		
		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			String path=params[0];
			byte[] data=HttpUtils.getMapData(path);
			return BitMapTools.decodebBitmapByByte(data, 200, 400);
		}
		
		@Override
		protected void onPostExecute(Bitmap bitmap) {
			// TODO Auto-generated method stub
			super.onPostExecute(bitmap);
			if(isCancelled()){
				bitmap=null;
			}
			if(imgsReference!=null&&bitmap!=null){
				final ImageView imageView=imgsReference.get();
				final BitmapWorkerTask bitmapWorkerTask=getBitmapWorkerTask(imageView);
				if(this==bitmapWorkerTask&&imageView!=null){
					imageView.setImageBitmap(bitmap);
				}
			}
		}
		
	}
	private static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView){
		if(imageView!=null){
			final Drawable drawable=imageView.getDrawable();
			if(drawable instanceof AsyncDrawable){
				final AsyncDrawable asyncDrawable=(AsyncDrawable)drawable;
				return asyncDrawable.getBitmapWorkerTask();
			}
		}
		return null;
	}
	public static boolean cancelPotntialWork(String data,ImageView imageView){
		final BitmapWorkerTask bitmapWorkerTask=getBitmapWorkerTask(imageView);
		if(bitmapWorkerTask!=null){
			final String bitmapdata=bitmapWorkerTask.data;
			if(bitmapdata!=data){
				bitmapWorkerTask.cancel(true);
			}else {
				return false;
			}
		}
		return true;
	}
	public void loadBitmap(String data,ImageView imageView){
		Bitmap place=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		if(cancelPotntialWork(data, imageView)){
			final BitmapWorkerTask task=new BitmapWorkerTask(imageView);
			final AsyncDrawable asyncDrawable=new AsyncDrawable(getResources(), place, task);
			imageView.setImageDrawable(asyncDrawable);
			task.execute(data);
		}
	}
}
