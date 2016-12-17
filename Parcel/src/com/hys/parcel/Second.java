package com.hys.parcel;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Second extends Activity {
	private TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		textView=(TextView)findViewById(R.id.textview);
		Person person=(Person)getIntent().getParcelableExtra("msg");
		textView.setText(person.toString());
		
	}
}
