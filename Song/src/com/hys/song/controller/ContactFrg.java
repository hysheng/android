package com.hys.song.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hys.song.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ContactFrg extends Fragment {
	private ListView contactList;
	private int[] headResIds = { R.drawable.contact_head,
			R.drawable.contact_head2, R.drawable.contact_head3,
			R.drawable.contact_head4, R.drawable.contact_head5 };
	private String[] contactNames = { "Yoona", "ÐìÏÍ", "Jessica", "Yuri", "IU" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.contact_f, null);
		init(view);
		return view;
	}

	private void init(View view) {
		contactList = (ListView) view.findViewById(R.id.contactlist);
		initListView();
	}

	private void initListView() {
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < headResIds.length; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("head", headResIds[i]);
			listItem.put("name", contactNames[i]);
			listItems.add(listItem);
		}
		SimpleAdapter contactsAdapter = new SimpleAdapter(getActivity(),
				listItems, R.layout.contact_item,
				new String[] { "head", "name" }, new int[] { R.id.contact_head,
						R.id.contact_name });
		contactList.setAdapter(contactsAdapter);
		contactList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent=new Intent();
				intent.setClass(getActivity(), ContactDetail.class);
				startActivity(intent);

			}
		});
	}
}
