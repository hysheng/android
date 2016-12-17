package com.hys.song.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hys.song.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MessageRefresh extends Activity implements OnClickListener{
	private ListView logistics;
	private ImageButton messageBack, messageRefresh;
	private SimpleAdapter adapter;
	private List<Map<String, Object>> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_refresh);
		initView();
		initData();

	}

	private void initView() {
		logistics = (ListView) findViewById(R.id.logistics);
		messageBack=(ImageButton)findViewById(R.id.message_back);
		messageRefresh=(ImageButton)findViewById(R.id.message_refresh);
		messageBack.setOnClickListener(this);
		messageRefresh.setOnClickListener(this);
		
	}

	private void initData() {
		data = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 4; i++) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("gods_name", "MacBook 1" + i);
			data.add(item);
		}
		adapter = new SimpleAdapter(this, data, R.layout.logistics_item,
				new String[] { "gods_name" }, new int[] { R.id.gods_name });
		logistics.setAdapter(adapter);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.message_back:
			finish();
			break;
		case R.id.message_refresh:
			onCreate(null);
		default:
			break;
		}		
	}
}
