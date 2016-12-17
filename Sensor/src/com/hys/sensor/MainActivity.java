package com.hys.sensor;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener,
		OnClickListener {
	private TextView show;
	private Button getSensor;
	private SensorManager manager;
	private Sensor mySensor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		show = (TextView) findViewById(R.id.show);
		getSensor = (Button) findViewById(R.id.button1);
		getSensor.setOnClickListener(this);
//		mySensor = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		mySensor=manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
//		mySensor = manager.getDefaultSensor(SensorManager.getOrientation(R,
//				values));
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
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
//		float distance = event.values[0];
//		show.append("距离是:\n" + distance + "cm");
		float value=event.values[0];
		if(value>=0&&value<45){
			show.setText("北偏东"+value+"度");
		}else if (value>=45&&value<90) {
			show.setText("东偏北"+(value-45)+"度");
		}else if (value>=90&&value<135) {
			show.setText("东偏南"+(value-90)+"度");
		}else if (value>=135&&value<180) {
			show.setText("南偏东"+(value-135)+"度");
		}else if (value>=180&&value<225) {
			show.setText("北偏西"+(value-180)+"度");
		}else if (value>=225&&value<270) {
			show.setText("西偏北"+(value-225)+"度");
		}else if (value>=270&&value<315) {
			show.setText("西偏南"+(value-270)+"度");
		}else if (value>=315&&value<360) {
			show.setText("南偏西"+(value-315)+"度");
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		manager.registerListener(this, mySensor,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		manager.unregisterListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		List<Sensor> list = manager.getSensorList(Sensor.TYPE_ALL);
		for (Sensor sensor : list) {
			show.append(" " + sensor.getName() + "\n");
		}

	}
}
