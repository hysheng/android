package com.hys.hughsmechanism;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

public class AsyncImageLoader {
	private ImageDiskCachUtil imageDiskCacheUtil;
	private MemoryCache memoryCache;
	private ExecutorService executorService;
	private Map<ImageView, String> imageViews = Collections
			.synchronizedMap(new WeakHashMap<ImageView, String>());
	private List<LoadImageTask> taskQueue=new ArrayList<LoadImageTask>();
	public AsyncImageLoader(Context context,MemoryCache memoryCache){
		this.memoryCache=memoryCache;
		imageDiskCacheUtil=new ImageDiskCachUtil(context);
		executorService=Executors.newFixedThreadPool(5);
		
	}
	public Bitmap loadBitmap(ImageView imageView,String url){
		imageViews.put(imageView, url);
		Bitmap bitmap=memoryCache.get(url);
		if(bitmap==null){
			addLoadImageQueue(imageView,url);
		}
		return bitmap;
	}
	private void addLoadImageQueue(ImageView imageView,String url){
		if(isTaskExisted(url))
		return;
		LoadImageTask task=new LoadImageTask(url, imageView);
		synchronized (taskQueue) {
			taskQueue.add(task);
		}
		executorService.execute(task);
	}
	private boolean isTaskExisted(String url){
		if(url==null){
			return false;	
		}
		synchronized (taskQueue) {
			int size=taskQueue.size();
			for(int i=0;i<size;i++){
				LoadImageTask task=taskQueue.get(i);
				if(task!=null&&task.getUrl().equals(url)){
					return true;
				}
			}
		}
		return false;
	}
	private Bitmap getBitmapByUrl(String url){
		Bitmap bitmap=imageDiskCacheUtil.readImageFromDiskCacheToMemory(url);
		if(bitmap!=null){
			return bitmap;
		}
		return loadBitmapFromWeb(url);
	}
	private Bitmap loadBitmapFromWeb(String url){
		InputStream inputStream = HttpUtil.getImageInputStream(url);
		imageDiskCacheUtil.writeImageFromMemoryToDiskCache(url,
				inputStream);
		//inputstream不能被读两次，第一次读的时候就会把数据读走
		return imageDiskCacheUtil.readImageFromDiskCacheToMemory(url);
		
	}
	private boolean imageViewReused(ImageView imageView,String url){
		String tag=imageViews.get(imageView);
		if(tag==null||!tag.equals(url))
			return true;
			return false;
	}
	private void removeTask(LoadImageTask task){
		synchronized (taskQueue) {
			taskQueue.remove(task);
		}
	}
	class LoadImageTask implements Runnable{
		private String url;
		private ImageView imageView;
		public LoadImageTask(String url,ImageView imageView){
			this.url=url;
			this.imageView=imageView;
		}
		@Override
		public void run() {
			if(imageViewReused(imageView, url)){
				removeTask(this);
				return;
			}
			Bitmap bitmap=getBitmapByUrl(url);
			memoryCache.put(url, bitmap);
			if(!imageViewReused(imageView, url)){
				BitmapDiplayer diplayer=new BitmapDiplayer(bitmap, imageView, url);
				Activity activity=(Activity)imageView.getContext();
				activity.runOnUiThread(diplayer);
			}
			removeTask(this);
		}
		
		public String getUrl(){
			return url;
		}
	}
	class BitmapDiplayer implements Runnable{
		private Bitmap bitmap;
		private ImageView imageView;
		private String url;
		public BitmapDiplayer(Bitmap bitmap,ImageView imageView,String url){
			this.bitmap=bitmap;
			this.imageView=imageView;
			this.url=url;
		}
		public void run(){
			if(imageViewReused(imageView, url))
				return;
			if(bitmap!=null)
				imageView.setImageBitmap(bitmap);
		}
	}
	public void destroy() {
	    memoryCache.clear();
	    memoryCache = null;
	    imageViews.clear();
	    imageViews = null;
	    taskQueue.clear();
	    taskQueue = null;
	    executorService.shutdown();
	    executorService = null;
	  }
}
