package com.hys.song.controller;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hys.song.R;
import com.hys.song.model.ContactsAdapter;

public class ContactFrg extends Fragment {
	private ListView contactList;
	private int[] headResIds = { R.drawable.contact_head,
			R.drawable.contact_head2, R.drawable.contact_head3,
			R.drawable.contact_head4, R.drawable.contact_head5,
			R.drawable.contact_head, R.drawable.contact_head2,
			R.drawable.contact_head3, R.drawable.contact_head4,
			R.drawable.contact_head5, R.drawable.contact_head,
			R.drawable.contact_head2, R.drawable.contact_head3 };
	private String[] contactNames;
	private String[] dayData;

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
		contactNames=getResources().getStringArray(R.array.contact_names_demo);
		dayData=getResources().getStringArray(R.array.contact_days_demo);
		initListView();
	}

	private void initListView() {
		
		ContactsAdapter contactsAdapter = new ContactsAdapter(getActivity(),
				headResIds, contactNames, dayData);
		contactList.setAdapter(contactsAdapter);
		contactList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), ContactDetail.class);
				startActivity(intent);

			}
		});
	}
}
