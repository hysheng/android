package com.hys.datepicker;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
	protected static final int DATE_DIALOG_ID = 0;
	private int mYear;
	private int mMonth;
	private int mDay;
	private TextView daTextView = null;
	private Button update = null;
	private DatePicker datePicker = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		daTextView = (TextView) findViewById(R.id.dateDisplay);
		update = (Button) findViewById(R.id.update);
		update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);

			}
		});
		final Calendar calendar = Calendar.getInstance();
		mYear = calendar.get(Calendar.YEAR);
		mMonth = calendar.get(Calendar.MONTH);
		mDay = calendar.get(Calendar.DAY_OF_MONTH);
		daTextView.setText(new StringBuilder().append("时间：").append(mYear)
				.append("-").append(mMonth).append("-").append(mDay));

		datePicker = (DatePicker) findViewById(R.id.datePicker);
		datePicker.init(mYear, mMonth, mDay, new OnDateChangedListener() {

			@Override
			public void onDateChanged(DatePicker view, int year,
					int monthOfYear, int dayOfMonth) {
				Toast.makeText(MainActivity.this,
						year + "-" + monthOfYear + "-" + dayOfMonth,
						Toast.LENGTH_SHORT).show();

			}
		});

	}

	private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			daTextView.setText(new StringBuilder().append("时间：").append(mYear).append("-")
					.append(mMonth).append("-").append(mDay));

		}
	};

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(MainActivity.this, listener, mYear,
					mMonth, mDay);
		}
		return null;
	}

}
