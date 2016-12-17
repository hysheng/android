package com.hys.tools;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitMapTools {

	public static Bitmap decodebBitmap(Resources resources, int resId,
			int reqWid, int reqHei) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		//�ڶ�λͼ���н���ʱ���Ȳ������ڴ�ռ�
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(resources, resId, options);
		//����ѹ������
		options.inSampleSize=calculateSimpleSize(options, reqWid, reqHei);
		//���ú�ѹ���������������λͼ
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
		//ѹ������
		int inSimpleSize=1;
		if(imageHeight>reqHei||imageWidth>reqWid){
			int heightRatio=Math.round((float)imageHeight/(float)reqHei);
			int widthRatio=Math.round((float)imageWidth/(float)reqWid);
			inSimpleSize=heightRatio>widthRatio?widthRatio:heightRatio;
		}
		return inSimpleSize;
		
	}
}
