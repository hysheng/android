package com.hys.viewpage;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//侧滑
//ViewPager有预加载的功能，当界面停留在当前页面的同时，也会加载下一个界面布局
public class Main extends Activity {
	
	private ViewPager viewPager;
	private List<View> views;
	private List<String> titles;
	private static final String FLAG="test";
	private LayoutInflater inflater;
	private View view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		viewPager=(ViewPager)findViewById(R.id.viewpager2);
		views=new ArrayList<View>();
		inflater=LayoutInflater.from(this);
		view=inflater.inflate(R.layout.tab1, null);
		views.add(view);
		titles=new ArrayList<String>();
		titles.add("MyPage");
		final MyAdapter adapter=new MyAdapter();
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				Log.v(FLAG, "onPageSelected被调用，传入参数值为："+arg0);
				Log.v(FLAG, "--------------");
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				Log.v(FLAG, "onPageScrolled被调用，传入参数arg0值为："+arg0);
				Log.v(FLAG, "onPageScrolled被调用，传入参数arg1值为："+arg1);
				Log.v(FLAG, "onPageScrolled被调用，传入参数arg2值为："+arg2);
				Log.v(FLAG, "--------------");
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				//这里可以开线程去下载网络数据，更新UI操作
				Log.v(FLAG, "onPageScrollStateChanged被调用，传入参数值为："+arg0);
				Log.v(FLAG, "--------------");
				View newView=inflater.inflate(R.layout.tab1, null);
				views.add(newView);
				titles.add("new title");
				adapter.notifyDataSetChanged();
				
			}
		});
		
	}
	class MyAdapter extends PagerAdapter{

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub.
			((ViewPager)container).addView(views.get(position));
			return views.get(position);
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			((ViewPager)container).removeView(views.get(position));
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return views.size();
		}
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return titles.get(position);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}
		
	}
}
