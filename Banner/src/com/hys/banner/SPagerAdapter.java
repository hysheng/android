package com.hys.banner;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class SPagerAdapter extends PagerAdapter {
	private Context context;
	private ArrayList<View> mListViews;

	public SPagerAdapter(Context context, ArrayList<View> mListViews) {
		super();
		this.context = context;
		this.mListViews = mListViews;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mListViews.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	@Override
	public Object instantiateItem(View container, int position) {
		// TODO Auto-generated method stub
		View view=mListViews.get(position);
		((ViewPager)container).addView(view);
		return view;
	}
	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager)container).removeView((View)object);
	}
	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return POSITION_NONE;
	}
}
