package com.hys.sensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Shake extends Activity implements OnClickListener,SensorEventListener {

	private Button clear;
	private TextView shakshow;
	private SensorManager manager;
	private Sensor sensor;
	private float lastX=0.0f;
	private final float SHOCK_THERSHOLD=20.0F;//晃动的幅度
	private final int COUNT=3;//连续晃动的次数达到3次，并没有什么用
	private int shockCount=0;
	private boolean shocked=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shake);
		manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensor=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		shakshow=(TextView)findViewById(R.id.shakeshow);
		clear=(Button)findViewById(R.id.clear);
		clear.setOnClickListener(this);
		

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		manager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		float x=event.values[0];
		if(Math.abs(x-lastX)>SHOCK_THERSHOLD){
			shockCount++;
			shocked=false;
			
		}
		lastX=x;
		shakshow.setText("你一共摇了：\n"+shockCount);
		if(!shocked&&shockCount>=3&&(shockCount%COUNT==0)){
			shocked=true;
			System.out.println("---------->摇一摇!");
		}

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		shocked=false;
		shockCount=0;
		lastX=0.0f;
		shakshow.setText("喝前摇一摇!");
		
	}

}
