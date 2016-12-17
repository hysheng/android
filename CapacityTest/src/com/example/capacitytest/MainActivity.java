package com.example.capacitytest;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	private Button bt1,bt2,bt3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt1=(Button)findViewById(R.id.bt1);
		bt2=(Button)findViewById(R.id.bt2);
		bt3=(Button)findViewById(R.id.bt3);
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		
	}

	public void method1() {
		int result = jisuan();
		System.out.println(result);
	}

	private int jisuan() {
		for (int i = 0; i < 10000; i++) {
			System.out.println(i);
		}
		return 1;
	}

	public void method2() {
		SystemClock.sleep(2000);
	}

	public void method3() {
		int sum = 0;
		for (int i = 0; i < 1000; i++) {
			sum += i;
		}
		System.out.println("sum=" + sum);
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
		case R.id.bt1:
			method1();
			break;
		case R.id.bt2:
			method2();
			break;
		case R.id.bt3:
			method3();
			break;

		default:
			break;
		}
		
	}
}
