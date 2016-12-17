package com.hys.banner;



import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public class Banner extends LinearLayout {
	private Context context;
	private Bitmap displayImage,hideImage; 
	private ViewPager viewPager;
	private SPagerAdapter sPagerAdapter;
	private ArrayList<View> mListViews;

	public Banner(Context context) {
		super(context);
		initView(context);
	}

	public Banner(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	private void initView(Context context) {
		this.context = context;
		RelativeLayout playViewLayout = (RelativeLayout) LayoutInflater.from(context).inflate(
				R.layout.bannert, null);
		this.setOrientation(LinearLayout.VERTICAL);
		this.setBackgroundResource(Color.rgb(255, 255, 255));
		addView(playViewLayout);
		
		displayImage=getBitmapFromSrc("play_display.png");
		hideImage=getBitmapFromSrc("play_hide.png");
		mListViews=new ArrayList<View>();
		viewPager=(ViewPager) playViewLayout.findViewById(R.id.viewpager);
		sPagerAdapter=new SPagerAdapter(context, mListViews);
		viewPager.setAdapter(sPagerAdapter);
		viewPager.setFadingEdgeLength(0);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});

	}

	private Bitmap getBitmapFromSrc(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
