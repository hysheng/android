package com.hys.banner;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.hys.song.view.SlidingPlayView;


public class MainActivity extends Activity {
	private SlidingPlayView banner;
	private ArrayList<View> advList;
	private int[] advIds = { R.drawable.show_m1, R.drawable.menu_viewpager_1,
			R.drawable.menu_viewpager_2, R.drawable.menu_viewpager_3,
			R.drawable.menu_viewpager_4, R.drawable.menu_viewpager_5 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        banner=(SlidingPlayView)findViewById(R.id.banner);
        banner.setPlayType(1);
        banner.setSleepTime(3000);
		advList = new ArrayList<View>();
		for (int i = 0; i < advIds.length; i++) {
			View view = LayoutInflater.from(this).inflate(
					R.layout.banner_item_layout, null);
			ImageView imageView=(ImageView)view.findViewById(R.id.banner_item);
			imageView.setImageResource(advIds[i]);
			advList.add(view);
		}
		banner.addViews(advList);
		banner.startPlay();
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
