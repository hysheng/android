package com.hys.preferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	private static final String TAG = "test";
	private EditText name;
	private EditText sex;
	private Button write;
	private Button read;
	private TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText) findViewById(R.id.name);
        sex=(EditText) findViewById(R.id.sex);
        write=(Button) findViewById(R.id.write);
        read=(Button) findViewById(R.id.read);
        show=(TextView) findViewById(R.id.show);
        Log.i(TAG, name.toString());
        write.setOnClickListener(listener);
        read.setOnClickListener(listener);
    }
    private OnClickListener listener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			SharedPreferences preferences=MainActivity.this.getSharedPreferences("mypres",
					Context.MODE_PRIVATE);
			switch (v.getId()) {
			case R.id.write:
				Editor editor=preferences.edit();
				editor.putString("name", name.getText().toString());
				editor.putString("sex", sex.getText().toString());
				editor.commit();
				Toast.makeText(MainActivity.this, "±£´æ³É¹¦", Toast.LENGTH_LONG).show();
				break;
			case R.id.read:
				String rname=preferences.getString("name", "NO");
				String rsex=preferences.getString("sex", "NO");
				String bir=preferences.getString("age", "NO");
				show.setText("Name="+rname+";Sex="+rsex+";age="+bir);
				break;
			default:
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
