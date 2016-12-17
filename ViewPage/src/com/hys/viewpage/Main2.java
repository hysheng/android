package com.hys.viewpage;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class Main2 extends FragmentActivity {
	private ViewPager viewPager;
	private List<Fragment> list;
	private List<String> titles;
	private MyAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);
		viewPager=(ViewPager)findViewById(R.id.viewpager);
		list=new ArrayList<Fragment>();
		titles=new ArrayList<String>();
		list.add(new Fragment1());
		list.add(new Fragment2());
		list.add(new Fragment3());
		titles.add("star1");
		titles.add("star2");
		titles.add("star3");
		adapter=new MyAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		
	}
	/*
	使用FragmentPagerAdapter适配器的时候，它会持久化数据，即使Fragment被系统销毁，数据也是会保留在内存中的，所以不能使用它来加载大量的数据
	使用FragmentStatePagerAdapter的时候，它会销毁Fragment仅仅保留Fragment的引用，适合加载一些的大量的数据
	  使用的话直接修改下面继承的类名即可
	  */
	class MyAdapter extends FragmentPagerAdapter{

		public MyAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int postion) {
			// TODO Auto-generated method stub
			return list.get(postion);
		}
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return titles.get(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}
		
	}
}
