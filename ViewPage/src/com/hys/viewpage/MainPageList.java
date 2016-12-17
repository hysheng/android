package com.hys.viewpage;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class MainPageList extends FragmentActivity {
	private ViewPager viewPager;
	private List<String> titles;
	private List<Fragment> list;
	private MyAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pagelist);
		viewPager=(ViewPager)findViewById(R.id.viewPager);
		titles=new ArrayList<String>();
		list=new ArrayList<Fragment>();
		titles.add("Page1");
		titles.add("Page2");
		list.add(new Fragment1());
		list.add(new FragmentPage());
		adapter=new MyAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		
		
				
	}
	class MyAdapter extends FragmentPagerAdapter{

		public MyAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return titles.get(position);
		}
	
		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return list.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}
		
	}
	
}
