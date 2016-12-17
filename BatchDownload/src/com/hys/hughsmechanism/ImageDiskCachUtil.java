package com.hys.hughsmechanism;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.hys.hughsmechanism.DiskLruCache.Snapshot;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;



public class ImageDiskCachUtil {
	private Context context;
	private DiskLruCache mDiskLruCache;
	private File cacheDir;
	private DiskCaCheUtil dcTools;

	public ImageDiskCachUtil(Context context) {
		this.context = context;
		dcTools = new DiskCaCheUtil();
		cacheDir = dcTools.getDiskCacheDir(this.context, "bitmap");
		if (!cacheDir.exists()) {
			cacheDir.mkdirs();
		}
		try {
			mDiskLruCache = DiskLruCache.open(cacheDir, 1, 1, 10 * 1024 * 1024);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public long getCacheSize() {
		return mDiskLruCache.size();
	}

	public void writeImageFromMemoryToDiskCache(String imgUrl,
			InputStream inputStream) {
		String key = DiskCaCheUtil.hashKeyForDisk(imgUrl);
		DiskLruCache.Editor editor;
		try {
			editor = mDiskLruCache.edit(key);
			if (editor != null) {
				OutputStream outputStream = editor.newOutputStream(0);
				if (dcTools.dataInputToOutStream(inputStream, outputStream)) {
					editor.commit();
					Log.v("test", "editor.commit();");
				} else {
					editor.abort();
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
	}

	public Bitmap readImageFromDiskCacheToMemory(String imgUrl) {
		Snapshot snapshot;
		Bitmap bitmap = null;
		String key = DiskCaCheUtil.hashKeyForDisk(imgUrl);
		try {
			snapshot = mDiskLruCache.get(key);
			if (snapshot != null) {
				InputStream inputStream = snapshot.getInputStream(0);
				bitmap = BitmapFactory.decodeStream(inputStream);
				Log.v("test", "2-----");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bitmap;
	}

	public void writeInJournal() {
		if (!mDiskLruCache.isClosed()) {
			try {
				mDiskLruCache.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void closeDiskLruCache() {
		if (!mDiskLruCache.isClosed()) {
			try {
				mDiskLruCache.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void delteDiskLruCache() {
		if (!mDiskLruCache.isClosed()) {
			try {
				mDiskLruCache.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
