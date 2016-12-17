package com.hys.radiobuttondemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;


public class MainActivity extends Activity {
	public RadioGroup radioGroup1=null;
	public RadioGroup radioGroup2=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup1=(RadioGroup) findViewById(R.id.sex);
        radioGroup2=(RadioGroup) findViewById(R.id.by);
        radioGroup1.setOnCheckedChangeListener(listener);
        radioGroup2.setOnCheckedChangeListener(listener);
        
    }
    private OnCheckedChangeListener listener=new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {
			case R.id.boy:
				Toast.makeText(MainActivity.this, "Boy", Toast.LENGTH_LONG).show();
				break;

			case R.id.girl:
				Toast.makeText(MainActivity.this, "Girl", Toast.LENGTH_LONG).show();
				break;
			case R.id.byes:
				Toast.makeText(MainActivity.this, "毕业了", Toast.LENGTH_LONG).show();
				break;
			case R.id.bno:
				Toast.makeText(MainActivity.this, "没有毕业", Toast.LENGTH_LONG).show();
				break;
			}
			
		}
	};

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
