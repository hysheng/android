package com.hys.image;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
/*
 * 从本地图库中选择图片，调用系统的工具对图片进行裁剪
 */
public class Main extends Activity implements OnClickListener {

	private Button select, cut;
	private ImageView img;
	private static final int IMAGE_SELECT = 1;
	private static final int IMAGE_CUT = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectandcut);
		select = (Button) findViewById(R.id.select);
		cut = (Button) findViewById(R.id.cut);
		img = (ImageView) findViewById(R.id.img);
		select.setOnClickListener(this);
		cut.setOnClickListener(this);
		Log.v("test", "@@@@@@@@");

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			if (requestCode == IMAGE_SELECT) {
				Uri uri = data.getData();
				Log.v("test", "uri的值为："+uri);
				int dw = getWindowManager().getDefaultDisplay().getWidth();
				int dh = getWindowManager().getDefaultDisplay().getHeight() / 2;
				Options factory = new Options();
				//factory.inJustDecodeBounds = true;
				try {
					Bitmap bitmap = BitmapFactory.decodeStream(
							getContentResolver().openInputStream(uri), null,
							factory);
					int wRatio = (int) Math.ceil(factory.outWidth / (float) dw);
					int hRatio = (int) Math
							.ceil(factory.outHeight / (float) dh);
					if (wRatio > 1 || hRatio > 1) {
						if (wRatio > hRatio) {
							factory.inSampleSize = wRatio;
						} else {
							factory.inSampleSize = hRatio;
						}
					}
					factory.inJustDecodeBounds = false;
					bitmap = BitmapFactory.decodeStream(getContentResolver()
							.openInputStream(uri), null, factory);
					img.setImageBitmap(bitmap);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (requestCode == IMAGE_CUT) {
				Bitmap bitmap = data.getParcelableExtra("data");
				img.setImageBitmap(bitmap);
			}
		}

	}

	@Override
	public void onClick(View v) {
		Intent intent;
		if (v.getId() == R.id.select) {
			intent = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(intent, IMAGE_SELECT);

		} else if (v.getId() == R.id.cut) {
			intent = getImageClipIntent();
			startActivityForResult(intent, IMAGE_CUT);
		}

	}

	private Intent getImageClipIntent() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
		intent.setType("image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 80);
		intent.putExtra("outputY", 80);
		intent.putExtra("return-data", true);
		return intent;
	}

}
