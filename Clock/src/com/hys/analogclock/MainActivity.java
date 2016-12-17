package com.hys.analogclock;

import java.util.Calendar;

import android.R.id;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Button tButton, dButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tButton = (Button) findViewById(R.id.timepicker);
		dButton = (Button) findViewById(R.id.datepicker);
		tButton.setOnClickListener(this);
		dButton.setOnClickListener(this);

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
		Calendar calendar=Calendar.getInstance();
		if (v.getId() == R.id.timepicker) {
			int minute=calendar.get(Calendar.MINUTE);
			int hourOfDay=calendar.get(Calendar.HOUR_OF_DAY);
			TimePickerDialog timePickerDialog = new TimePickerDialog(
					MainActivity.this, new OnTimeSetListener() {

						@Override
						public void onTimeSet(TimePicker view, int hourOfDay,
								int minute) {
							Toast.makeText(MainActivity.this, hourOfDay+":"+minute, 1).show();

						}
					}, hourOfDay, minute, true);
			timePickerDialog.show();
		} else {
			int monthOfYear=calendar.get(Calendar.MONTH);
			int year=calendar.get(Calendar.YEAR);
			int dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);
			DatePickerDialog datePickerDialog=new DatePickerDialog(MainActivity.this, new OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					Toast.makeText(MainActivity.this, year+"-"+monthOfYear+"-"+dayOfMonth, 1).show();
					
				}
			}, year, monthOfYear, dayOfMonth);
			datePickerDialog.show();
		}

	}
}
