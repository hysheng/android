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
	ʹ��FragmentPagerAdapter��������ʱ������־û����ݣ���ʹFragment��ϵͳ���٣�����Ҳ�ǻᱣ�����ڴ��еģ����Բ���ʹ���������ش���������
	ʹ��FragmentStatePagerAdapter��ʱ����������Fragment��������Fragment�����ã��ʺϼ���һЩ�Ĵ���������
	  ʹ�õĻ�ֱ���޸�����̳е���������
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
