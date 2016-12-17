package com.hys.mylock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final Intent intent=new Intent(this, LockService.class);
		Button start=(Button)findViewById(R.id.start);
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startService(intent);
			}
		});
		
	}

}
