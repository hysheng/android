package com.hys.viewpage;

import com.hys.viewpage.ImageDownLoad.ImageCallBack;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Fragment1 extends Fragment {
	private String img_path="http://img3.duitang.com/uploads/item/201507/14/20150714115929_HutGf.thumb.700_0.jpeg";
//	private String img_path="https://www.baidu.com/img/bd_logo1.png";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.tab1, null);
		final ImageView imageView=(ImageView) view.findViewById(R.id.img);
		new ImageDownLoad().loadImage(img_path, new ImageCallBack() {
			
			@Override
			public void getImageContent(Bitmap bitmap) {
				// TODO Auto-generated method stub
				imageView.setImageBitmap(bitmap);
				
				
			}
		});
		return view;
	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
}
