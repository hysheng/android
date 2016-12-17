package com.hys.image;

import java.io.InputStream;

import com.hys.tools.HttpTools;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class GetImageByInternet extends Activity implements OnClickListener{
	
	private Button getimg;
	private ImageView img;
	private static final String URL_PATH="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.getimagebyinternet);
		getimg=(Button)findViewById(R.id.getImage);
		img=(ImageView)findViewById(R.id.imgOnInternet);
		getimg.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		InputStream is=HttpTools.getImageInputStream(URL_PATH);
		Bitmap bitmap=BitmapFactory.decodeStream(is);
		img.setImageBitmap(bitmap);
	}
	
}
