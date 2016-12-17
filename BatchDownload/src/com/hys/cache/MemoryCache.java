package com.hys.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import android.graphics.Bitmap;

/**
 * ʵ��ͼƬ�ĸ��ٻ���<br>
 * ������1��map������ͼƬ
 */
public class MemoryCache {
	/** ���Ļ����� */
	private static final int MAX_CACHE_CAPACITY = 30;
	/**
	 * ����map�����������������涨��С�������������뻺���
	 */
	private HashMap<String, SoftReference<Bitmap>> mCacheMap = new LinkedHashMap<String, SoftReference<Bitmap>>() {
		private static final long serialVersionUID = 1L;

		// ��дLinkedHashMap�ķ�������map���������涨��С��30������������������Ԫ��
		protected boolean removeEldestEntry(
				Map.Entry<String, SoftReference<Bitmap>> eldest) {
			return size() > MAX_CACHE_CAPACITY;
		};
	};

	/**
	 * �ӻ�����ȡ��ͼƬ
	 * 
	 * @param id
	 * @return ��������У����Ҹ�ͼƬû���ͷţ��򷵻ظ�ͼƬ�����򷵻�null
	 */
	public Bitmap get(String id) {
		if (!mCacheMap.containsKey(id))
			return null;
		SoftReference<Bitmap> ref = mCacheMap.get(id);
		return ref.get();
	}

	/**
	 * ��ͼƬ���뻺��
	 * 
	 * @param id
	 * @param bitmap
	 */
	public void put(String id, Bitmap bitmap) {
		mCacheMap.put(id, new SoftReference<Bitmap>(bitmap));
	}

	/**
	 * ������л���
	 */
	public void clear() {
		try {
			for (Map.Entry<String, SoftReference<Bitmap>> entry : mCacheMap
					.entrySet()) {
				SoftReference<Bitmap> sr = entry.getValue();
				if (null != sr) {
					Bitmap bmp = sr.get();
					if (null != bmp)
						bmp.recycle();
				}
			}
			mCacheMap.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}