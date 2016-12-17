package com.hys.imageswitcher;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity implements OnClickListener,
		ViewFactory {

	private Button forward, next;
	private ImageSwitcher imageSwitcher;
	private int index = 0;
	private int[] list = new int[] { R.drawable.star1, R.drawable.star2,
			R.drawable.star3, R.drawable.star4, R.drawable.star5,
			R.drawable.star6};

	// private List<Drawable> list=new ArrayList<Drawable>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		forward = (Button) findViewById(R.id.forward);
		next = (Button) findViewById(R.id.next);
		imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
//		list.add(getResources().getDrawable(R.drawable.star1));
//		list.add(getResources().getDrawable(R.drawable.star2));
//		list.add(getResources().getDrawable(R.drawable.star3));
//		list.add(getResources().getDrawable(R.drawable.star4));
//		list.add(getResources().getDrawable(R.drawable.star5));
//		list.add(getResources().getDrawable(R.drawable.star6));
		imageSwitcher.setFactory(this);
		forward.setOnClickListener(this);
		next.setOnClickListener(this);
		// 初始化图片的加载
		if(list.length>0){
			imageSwitcher.setImageResource(list[index]);
		}
//		if (list.size() > 0) {
//			imageSwitcher.setImageDrawable(list.get(0));
//		}
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

	@Override
	public View makeView() {
		// TODO Auto-generated method stub
		return new ImageView(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.forward) {
			index--;
			if (index < 0) {
//				index = list.size() - 1;
				index=list.length-1;
			}
//			imageSwitcher.setImageDrawable(list.get(index));
			imageSwitcher.setImageResource(list[index]);
		} else if (v.getId() == R.id.next) {
			index++;
			if(index>=list.length){
				index=0;
			}
			imageSwitcher.setImageResource(list[index]);
//			if (index >= list.size()) {
//				index = 0;
//			}
//			imageSwitcher.setImageDrawable(list.get(index));
		}

	}
}
