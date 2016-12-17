package com.hys.cache;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
/**
 * �첽����ͼƬ��Դ
 *
 */
public class AsyncImageLoader{
	private MemoryCache mMemoryCache;//�ڴ滺��
	private FileCache mFileCache;//�ļ�����
	private ExecutorService mExecutorService;//�̳߳�
	private Map<ImageView, String> mImageViews = Collections
			.synchronizedMap(new WeakHashMap<ImageView, String>());//��¼�Ѿ�����ͼƬ��ImageView
	private List<LoadPhotoTask> mTaskQueue = new ArrayList<LoadPhotoTask>();//�������ڼ���ͼƬ��url 
	/**
	 * Ĭ�ϲ���һ����СΪ5���̳߳�
	 * @param context
	 * @param memoryCache �����õĸ��ٻ���
	 * @param fileCache �����õ��ļ�����
	 */
	public AsyncImageLoader(Context context, MemoryCache memoryCache, FileCache fileCache) {
		mMemoryCache = memoryCache;
		mFileCache = fileCache;
		mExecutorService = Executors.newFixedThreadPool(5);//����һ������Ϊ5�Ĺ̶��ߴ���̳߳أ�����������е��߳�������
	}

	/**
	 * ����url������Ӧ��ͼƬ
	 * @param url
	 * @return �������������ֱ�ӷ��أ����û�����첽���ļ�������˻�ȡ
	 */
	public Bitmap loadBitmap(ImageView imageView, String url) {
		mImageViews.put(imageView, url);//�Ƚ�ImageView��¼��Map��,��ʾ��ui�Ѿ�ִ�й�ͼƬ������
		Bitmap bitmap = mMemoryCache.get(url);//�ȴ�һ�������л�ȡͼƬ
		if(bitmap == null) {
			enquequeLoadPhoto(url, imageView);
		}
		return bitmap;
	}
	
	/**
	 * ����ͼƬ���ض���
	 * @param url
	 */
	private void enquequeLoadPhoto(String url, ImageView imageView) {
		//��������Ѿ����ڣ����������
		if(isTaskExisted(url))
			return;
		LoadPhotoTask task = new LoadPhotoTask(url, imageView);
		synchronized (mTaskQueue) {
			mTaskQueue.add(task);//��������ӵ�������			
		}
		mExecutorService.execute(task);//���̳߳����ύ�������û�дﵽ����(5),�����з�������
	}
	
	/**
	 * �ж����ض������Ƿ��Ѿ����ڸ�����
	 * @param url
	 * @return
	 */
	private boolean isTaskExisted(String url) {
		if(url == null)
			return false;
		synchronized (mTaskQueue) {
			int size = mTaskQueue.size();
			for(int i=0; i<size; i++) {
				LoadPhotoTask task = mTaskQueue.get(i);
				if(task != null && task.getUrl().equals(url))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * �ӻ����ļ���������˻�ȡͼƬ
	 * @param url
	 */
	private Bitmap getBitmapByUrl(String url) {
		File f = mFileCache.getFile(url);//��û���ͼƬ·��
		Bitmap b = ImageUtil.decodeFile(f);//����ļ���Bitmap��Ϣ
		if (b != null)//��Ϊ�ձ�ʾ����˻�����ļ�
			return b;
		return ImageUtil.loadBitmapFromWeb(url, f);//ͬ������ͼƬ
	}
	
	/**
	 * �жϸ�ImageView�Ƿ��Ѿ����ع�ͼƬ�ˣ��������ж��Ƿ���Ҫ���м���ͼƬ��
	 * @param imageView
	 * @param url
	 * @return
	 */
	private boolean imageViewReused(ImageView imageView, String url) {
		String tag = mImageViews.get(imageView);
		if (tag == null || !tag.equals(url))
			return true;
		return false;
	}
	
	private void removeTask(LoadPhotoTask task) {
		synchronized (mTaskQueue) {
			mTaskQueue.remove(task);
		}
	}
	
	class LoadPhotoTask implements Runnable {
		private String url;
		private ImageView imageView;	
		LoadPhotoTask(String url, ImageView imageView) {
			this.url = url;
			this.imageView = imageView;
		}
		
		@Override
		public void run() {
			if (imageViewReused(imageView, url)) {//�ж�ImageView�Ƿ��Ѿ�������
				removeTask(this);//����Ѿ���������ɾ������
				return;
			}
			Bitmap bmp = getBitmapByUrl(url);//�ӻ����ļ���������˻�ȡͼƬ
			mMemoryCache.put(url, bmp);//��ͼƬ���뵽һ��������
			if (!imageViewReused(imageView, url)) {//��ImageViewδ��ͼƬ����ui�߳�����ʾͼƬ
				BitmapDisplayer bd = new BitmapDisplayer(bmp, imageView, url);//����run()ʵ������ImageViewͼƬ�����
				Activity a = (Activity) imageView.getContext();
				a.runOnUiThread(bd);//��UI�̵߳���bd�����run������ʵ��ΪImageView�ؼ�����ͼƬ
			}
			removeTask(this);//�Ӷ������Ƴ�����
		}
		
		public String getUrl() {
			return url;
		}
	}
	
	/**
	 * 
	 *��UI�߳���ִ�и������run����
	 */
	class BitmapDisplayer implements Runnable {
		private Bitmap bitmap;
		private ImageView imageView;
		private String url;
		public BitmapDisplayer(Bitmap b, ImageView imageView, String url) {
			bitmap = b;
			this.imageView = imageView;
			this.url = url;
		}
		public void run() {
			if (imageViewReused(imageView, url))
				return;
			if (bitmap != null)
				imageView.setImageBitmap(bitmap);
		}
	}
	
	/**
	 * �ͷ���Դ
	 */
	public void destroy() {
		mMemoryCache.clear();
		mMemoryCache = null;
		mImageViews.clear();
		mImageViews = null;
		mTaskQueue.clear();
		mTaskQueue = null;
		mExecutorService.shutdown();
		mExecutorService = null;
	}
}