package com.hys.camera;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Upload extends Activity implements OnClickListener {
	//写的是内网IP，在内网使用
	private Button takePhoto;
	private Button upload;
	private ImageView imageView;
	private String path="http://172.18.5.42:8080/Upload/servlet/UploadAction";
	private byte[] img_data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upload);
		takePhoto = (Button) findViewById(R.id.takephoto);
		upload = (Button) findViewById(R.id.upload);
		imageView = (ImageView) findViewById(R.id.imageView1);
		takePhoto.setOnClickListener(this);
		upload.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		Intent intent;
		if (view.getId() == R.id.takephoto) {
			intent = new Intent(
					android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent, 1001);
		} else {
			HttpPostUtils.sendFormByPost(path, img_data, "a.png");

		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1001 && resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			Bitmap bitmap = (Bitmap) bundle.get("data");
			ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
			img_data=outputStream.toByteArray();
			imageView.setImageBitmap(bitmap);
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
