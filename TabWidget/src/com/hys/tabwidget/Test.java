package com.hys.tabwidget;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Test extends ListActivity {
	private final String[] CON= {"1","2","3","4"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,CON));
		ListView lv=getListView();
		lv.setTextFilterEnabled(true);
		Log.v("test", "Test ±»µ÷ÓÃ");
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(), ((TextView)view).getText(), Toast.LENGTH_SHORT).show();
				
			}
		});
}
}
