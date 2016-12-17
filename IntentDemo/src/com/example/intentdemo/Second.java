package com.example.intentdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Second extends Activity {
	
	public final static int RESULT_CODE=1;
	private Button bt1=null;
	private TextView tv=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		bt1=(Button)findViewById(R.id.second_bt1);
		tv=(TextView)findViewById(R.id.tv);
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		String text=bundle.getString("str");
		tv.setText(text);
		bt1.setOnClickListener(listener);
		
	}
	
	private OnClickListener listener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.putExtra("back", "Back Data");
			setResult(RESULT_CODE, intent);
			finish();
		}
	};
}
