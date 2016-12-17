package com.hys.viewpage;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class MainActivity extends Activity {
	
	private ViewPager viewPager;
	private PagerTitleStrip pagerTitleStrip;
	private List<View> views;
	private List<String> titles;
	private View layout,layout2,layout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        pagerTitleStrip=(PagerTitleStrip)findViewById(R.id.pagetitle);
        titles=new ArrayList<String>();
        titles.add("One");
        titles.add("Two");
        titles.add("Three");
        layout=LinearLayout.inflate(this, R.layout.tab1, null);
        layout2=LinearLayout.inflate(this, R.layout.tab2, null);
        layout3=LinearLayout.inflate(this, R.layout.tab3, null);
        views=new ArrayList<View>();
        views.add(layout);
        views.add(layout2);
        views.add(layout3);
        viewPager.setAdapter(new Adapter());
    }
     
    class Adapter extends PagerAdapter{
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return views.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			((ViewPager)container).removeView(views.get(position));
		}

		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return titles.get(position);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			((ViewPager)container).addView(views.get(position));
			return views.get(position);
		}
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
