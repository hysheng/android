package com.hys.gridview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageActivity extends Activity {
	private ImageView imageView;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageview);
		imageView=(ImageView)findViewById(R.id.imageview);
		Intent intent=getIntent();
		imageView.setImageResource(intent.getIntExtra("id", R.drawable.o));
		
		
	}

}
