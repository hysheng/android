package com.hys.handler_databy_internet.tools;

import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

public class DownloadImage {

	private String img_path;

	public DownloadImage(String img_path) {
		this.img_path = img_path;
	}

	public void loadImage(final ImageCallBack imageCallBack) {
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if(msg.what==1){
				imageCallBack.setImageBitmap((Bitmap) msg.obj);
				}
			}

		};
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Bitmap bitmap = BitmapFactory.decodeStream(new URL(img_path)
					.openStream());
					Message message=Message.obtain();
					message.what=1;
					message.obj=bitmap;
					handler.sendMessage(message);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public interface ImageCallBack {
		public void setImageBitmap(Bitmap bitmap);
	}
}
