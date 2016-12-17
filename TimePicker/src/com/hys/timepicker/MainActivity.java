package com.hys.timepicker;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	private Button updateButton;
	private TextView display;
	private int hour;
	private int mminute;

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		if (id == 0) {
			return new TimePickerDialog(MainActivity.this, listener, hour,
					mminute, true);
		} else {
			return null;
		}

	}

	private TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			hour = hourOfDay;
			mminute = minute;
			display.setText(new StringBuffer().append(hour).append(":")
					.append(mminute));
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		updateButton = (Button) findViewById(R.id.update);
		display = (TextView) findViewById(R.id.display);
		final Calendar calendar = Calendar.getInstance();
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		mminute = calendar.get(Calendar.MINUTE);

		display.setText(new StringBuilder().append(hour).append(":")
				.append(mminute));
		updateButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(0);

			}
		});
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
