package com.hys.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {

	private ImageView imageView;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private Animation alpha;
	private Animation scale;
	private Animation translate;
	private Animation rotate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		button1=(Button)findViewById(R.id.button1);
		button2=(Button)findViewById(R.id.button2);
		button3=(Button)findViewById(R.id.button3);
		button4=(Button)findViewById(R.id.button4);
		button5=(Button)findViewById(R.id.button5);
		imageView=(ImageView)findViewById(R.id.imageView1);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
		button5.setOnClickListener(this);
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			alpha = new AlphaAnimation(0.1f, 1.0f);
			alpha.setDuration(3000);
			imageView.setAnimation(alpha);
			break;
		case R.id.button2:
			scale=new ScaleAnimation(0.1f, 1.0f, 0.1f, 1.0f);
			scale.setDuration(3000);
			imageView.setAnimation(scale);
			break;
		case R.id.button3:
			translate=new TranslateAnimation(0.1f, 100.0f, 10.0f, 200.0f);
			translate.setDuration(3000);
			imageView.setAnimation(translate);
			break;
		case R.id.button4:
			rotate=new RotateAnimation(0, 360);
			rotate.setDuration(3000);
			imageView.setAnimation(rotate);
			break;
		case R.id.button5:
			
			scale=new ScaleAnimation(0.1f, 1.0f, 0.1f, 1.0f);
			rotate=new RotateAnimation(0, 360);
			AnimationSet set=new AnimationSet(true);
			set.addAnimation(scale);
			set.addAnimation(rotate);
			set.setDuration(3000);
			imageView.setAnimation(set);
			break;

		}

	}
}
