package com.hys.multiplescreens;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ContentFragment extends ListFragment {
	private ArrayAdapter<String> adapter;

	public List<String> getData() {
		List<String> list = new ArrayList<String>();
		for (int i = 1; i < 16; i++) {
			list.add("ÕýÎÄ"+i);
		}
		return list;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, getData());

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setListAdapter(adapter);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

}
