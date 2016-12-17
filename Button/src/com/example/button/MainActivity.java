package com.example.button;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener,
		OnTouchListener, OnFocusChangeListener, OnKeyListener {

	private int onclick_flag=0;
	private int ontouch_flag=0;
	private int onkey_flag=0;
	private int onfocuschange_flag=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button test = (Button) findViewById(R.id.test);
		test.setOnClickListener(this);
		test.setOnTouchListener(this);
		test.setOnFocusChangeListener(this);
		test.setOnKeyListener(this);

		
		

	}

	@Override
	public boolean onKey(View v, int arg1, KeyEvent event) {
		// TODO Auto-generated method stub
		onkey_flag++;
		if(KeyEvent.ACTION_DOWN==event.getAction()){
			v.setBackgroundResource(R.drawable.button2);
		}else if(KeyEvent.ACTION_UP==event.getAction()){
			v.setBackgroundResource(R.drawable.button3);
		}
		Log.v("test", "onKey()被执行"+onkey_flag+"次");
		return false;
	}

	@Override
	public void onFocusChange(View v, boolean facusCode) {
		// TODO Auto-generated method stub
		onfocuschange_flag++;
		if(facusCode){
			v.setBackgroundResource(R.drawable.button3);
			Log.v("test", "onFocusChange()被执行"+onfocuschange_flag+"次"+"----"+facusCode);
		}else {
			v.setBackgroundResource(R.drawable.button2);
			Log.v("test", "onFocusChange()被执行"+onfocuschange_flag+"次"+"----"+facusCode);
		}
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		ontouch_flag++;
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			v.setBackgroundResource(R.drawable.button1);
		}else if (event.getAction()==MotionEvent.ACTION_UP) {
			v.setBackgroundResource(R.drawable.button2);
		}
		Log.v("test", "onTouch()被执行"+ontouch_flag+"次");
		return false;
	}

	@Override
	public void onClick(View arg0) {
		onclick_flag++;
		Button bt = (Button) arg0;
		bt.setBackgroundResource(R.drawable.button1);
		Log.v("test", "onClick()被执行"+onclick_flag+"次");

	}

}
