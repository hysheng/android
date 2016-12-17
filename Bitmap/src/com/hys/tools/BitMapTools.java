package com.hys.tools;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitMapTools {

	public static Bitmap decodebBitmap(Resources resources, int resId,
			int reqWid, int reqHei) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		//在对位图进行解码时，先不申请内存空间
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(resources, resId, options);
		//设置压缩比例
		options.inSampleSize=calculateSimpleSize(options, reqWid, reqHei);
		//设置好压缩比例后真正输出位图
		options.inJustDecodeBounds=false;
		return BitmapFactory.decodeResource(resources, resId, options);
	}
	public static Bitmap decodebBitmapByByte(byte[] data,int reqWid, int reqHei) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeByteArray(data, 0, data.length, options);
		options.inSampleSize=calculateSimpleSize(options, reqWid, reqHei);
		options.inJustDecodeBounds=false;
		return BitmapFactory.decodeByteArray(data, 0, data.length, options);
	}

	public static int calculateSimpleSize(BitmapFactory.Options options,
			int reqWid, int reqHei) {
		int imageHeight=options.outHeight;
		int imageWidth=options.outWidth;
		//压缩比例
		int inSimpleSize=1;
		if(imageHeight>reqHei||imageWidth>reqWid){
			int heightRatio=Math.round((float)imageHeight/(float)reqHei);
			int widthRatio=Math.round((float)imageWidth/(float)reqWid);
			inSimpleSize=heightRatio>widthRatio?widthRatio:heightRatio;
		}
		return inSimpleSize;
		
	}
}
