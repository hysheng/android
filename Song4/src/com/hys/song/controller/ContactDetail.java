package com.hys.song.controller;

import com.bumptech.glide.Glide;
import com.hys.song.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactDetail extends Activity {
	private ImageView headImage;
	private TextView detailName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_detail);
		initView();
		initData();
	}

	public void initView() {
		headImage = (ImageView) findViewById(R.id.contact_detail_head_image);
		detailName = (TextView) findViewById(R.id.contact_detail_name);

	}

	public void initData() {
		Glide.with(this).load(R.drawable.contact_head).into(headImage);

	}
}
