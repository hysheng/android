package com.hys.recyclerview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	private RecyclerView recyclerView;
	private LinearLayoutManager layoutManager;
	private RecyclerAdapter adapter;
	private List<String> datas;
	private Button insert,remove;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
		layoutManager = new LinearLayoutManager(this);
		insert=(Button)findViewById(R.id.insert);
		remove=(Button)findViewById(R.id.remove);
		insert.setOnClickListener(this);
		remove.setOnClickListener(this);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setHasFixedSize(true);
		datas=new ArrayList<String>();
		String[] strings=getResources().getStringArray(R.array.iphones);
		for(int i=0;i<strings.length;i++){
			datas.add(strings[i]);
		}
		adapter = new RecyclerAdapter(this, datas);
		adapter.setOnItemClickListener(new RecyclerAdapter.OnRecyclerViewItemClickListener() {
			
			@Override
			public void onItemClick(View view,String tag) {

				Toast.makeText(MainActivity.this,tag, Toast.LENGTH_SHORT).show();
			}
		});
		recyclerView.setAdapter(adapter);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.insert:
			adapter.addItem("surface");
			break;
		case R.id.remove:
			adapter.removeItem("iPhone 4");
			break;

		default:
			break;
		}
		
	}

}
