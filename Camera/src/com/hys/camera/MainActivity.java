package com.hys.camera;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {

	private Button takePhoto;
	private Button browse;
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		takePhoto = (Button) findViewById(R.id.takephoto);
		browse = (Button) findViewById(R.id.browse);
		imageView = (ImageView) findViewById(R.id.imageView1);
		takePhoto.setOnClickListener(this);
		browse.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		Intent intent;
		if (view.getId() == R.id.takephoto) {
			intent = new Intent(
					android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent, 1000);
		} else {
			intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType("image/*");
			intent.putExtra("crop", true);
			intent.putExtra("return-data", true);
			startActivityForResult(intent, 1001);

		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1000 && resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			Bitmap bitmap = (Bitmap) bundle.get("data");
			imageView.setImageBitmap(bitmap);
		} else if (requestCode == 1001 && resultCode == RESULT_OK) {
			Uri uri = data.getData();
			ContentResolver resolver = getContentResolver();
			try {
				Bitmap bitmap = BitmapFactory.decodeStream(resolver
						.openInputStream(uri));
				imageView.setImageBitmap(bitmap);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
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
