package com.hys.mylock;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LockView extends Activity {
	protected static final String TAG = "test";
	private Button doB;
	private Button re;
	private Button mi;
	private Button fa;
	private Button so;
	private Button la;
	private Button si;
	private EditText pdEditText;
	private String password="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lockview);
		pdEditText=(EditText)findViewById(R.id.password);
		doB=(Button)findViewById(R.id.Do);
		re=(Button)findViewById(R.id.re);
		mi=(Button)findViewById(R.id.mi);
		fa=(Button)findViewById(R.id.fa);
		so=(Button)findViewById(R.id.so);
		la=(Button)findViewById(R.id.la);
		si=(Button)findViewById(R.id.si);
		doB.setOnClickListener(listener);
		re.setOnClickListener(listener);
		mi.setOnClickListener(listener);
		fa.setOnClickListener(listener);
		so.setOnClickListener(listener);
		la.setOnClickListener(listener);
		si.setOnClickListener(listener);
		
				
	}
	private OnClickListener listener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			StringBuffer tools=new StringBuffer(password);
			switch (v.getId()) {
			case R.id.Do:
				password=tools.append("1").toString();
				break;
			case R.id.re:
				password=tools.append("2").toString();
				break;
			case R.id.mi:
				password=tools.append("3").toString();
				break;
			case R.id.fa:
				password=tools.append("4").toString();
				break;
			case R.id.so:
				password=tools.append("5").toString();
				break;
			case R.id.la:
				password=tools.append("6").toString();
				break;
			case R.id.si:
				password=tools.append("7").toString();
				break;

			default:
				break;
			}
			Log.i(TAG, password);
			pdEditText.setText(password);
		}
	};
}
