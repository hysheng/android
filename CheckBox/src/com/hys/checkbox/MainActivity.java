package com.hys.checkbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends Activity {
	private CheckBox checkBox1 = null;
	private CheckBox checkBox2 = null;
	private CheckBox checkBox3 = null;
	private CheckBox checkBox4 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		checkBox1 = (CheckBox) findViewById(R.id.cJava);
		checkBox2 = (CheckBox) findViewById(R.id.cC);
		checkBox3 = (CheckBox) findViewById(R.id.cCSharp);
		checkBox4 = (CheckBox) findViewById(R.id.chtml);
		checkBox1.setOnCheckedChangeListener(listener);
		checkBox2.setOnCheckedChangeListener(listener);
		checkBox3.setOnCheckedChangeListener(listener);
		checkBox4.setOnCheckedChangeListener(listener);

	}

	private OnCheckedChangeListener listener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			String info = buttonView.getText().toString();
			switch (buttonView.getId()) {
			case R.id.cC:
				Toast.makeText(MainActivity.this, info, Toast.LENGTH_SHORT)
						.show();
				break;

			case R.id.cCSharp:
				Toast.makeText(MainActivity.this, info, Toast.LENGTH_SHORT)
						.show();
				break;
			case R.id.chtml:
				Toast.makeText(MainActivity.this, info, Toast.LENGTH_SHORT)
						.show();
				break;
			case R.id.cJava:
				Toast.makeText(MainActivity.this, info, Toast.LENGTH_SHORT)
						.show();
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
