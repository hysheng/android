package com.hys.listview;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

//	private String[] contries=new String[]{"China","Korea","Japan","USA","Ireland","UK"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.my);
		String[] contries=getResources().getStringArray(R.array.countries);
		setListAdapter(new ArrayAdapter<String>(this,R.layout.list_item, contries));
		ListView lv=getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, ((TextView)view).getText().toString(), Toast.LENGTH_SHORT).show();
				
			}
		});
	}

}
