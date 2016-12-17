package com.hys.listview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Demo extends Activity {

	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mydemo);
		final List<String> list = new ArrayList<String>();
		list.add("Beijing");
		list.add("Shanghai");
		list.add("Shenzheng");
		listView = (ListView) findViewById(R.id.demo);
		ListAdapter adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_selectable_list_item, list);
		listView.setAdapter(adapter);
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(Demo.this, "你所选择的是" + list.get(position), 1).show();
				return false;
			}

		});

	}
}
