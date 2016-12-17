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

//�໬
//ViewPager��Ԥ���صĹ��ܣ�������ͣ���ڵ�ǰҳ���ͬʱ��Ҳ�������һ�����沼��
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
				Log.v(FLAG, "onPageSelected�����ã��������ֵΪ��"+arg0);
				Log.v(FLAG, "--------------");
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				Log.v(FLAG, "onPageScrolled�����ã��������arg0ֵΪ��"+arg0);
				Log.v(FLAG, "onPageScrolled�����ã��������arg1ֵΪ��"+arg1);
				Log.v(FLAG, "onPageScrolled�����ã��������arg2ֵΪ��"+arg2);
				Log.v(FLAG, "--------------");
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				//������Կ��߳�ȥ�����������ݣ�����UI����
				Log.v(FLAG, "onPageScrollStateChanged�����ã��������ֵΪ��"+arg0);
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
