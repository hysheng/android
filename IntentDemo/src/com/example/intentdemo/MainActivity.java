package com.example.intentdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

	

	private Button bt1=null;
	private Button bt2=null;
	private Button bt3=null;
	private final static int REQUEST_CODE=1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1=(Button)findViewById(R.id.bt1);
        bt1.setOnClickListener(listener);
        bt2=(Button)findViewById(R.id.bt2);
        bt2.setOnClickListener(listener);
        bt3=(Button)findViewById(R.id.bt3);
        bt3.setOnClickListener(listener);
        
        
    }

    private OnClickListener listener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Button bt=(Button)v;
			Intent intent=new Intent();
			switch (bt.getId()) {
//			case R.id.bt1:
//				System.o
//				break;

			case R.id.bt2:
				intent.setAction(Intent.ACTION_SENDTO);
				intent.setData(Uri.parse("smsto:5554"));
				intent.putExtra("sms_body", "This is Hugh");
				startActivity(intent);
				break;
			case R.id.bt3:
				intent.setClass(MainActivity.this, Second.class);
				intent.putExtra("str", "IntentDemo");
				startActivityForResult(intent, REQUEST_CODE);
			}
			
			
		}
	};
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode==REQUEST_CODE){
			if(resultCode==Second.RESULT_CODE){
				Bundle bundle=data.getExtras();
				String str=bundle.getString("back");
				Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
			}
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
