package com.hys.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CatalogFragment extends ListFragment {

	private ArrayAdapter<String> adapter;
	private FragmentManager manager;
	private FragmentTransaction transaction;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, getData());
		manager=getFragmentManager();

	}

	public List<String> getData() {
		List<String> list = new ArrayList<String>();
		for (int i = 1; i < 41; i++) {
			list.add("chapter" + i);
		}
		return list;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.catalog, null);
		setListAdapter(adapter);
		adapter.notifyDataSetChanged();
		return view;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String item=adapter.getItem(position);
		transaction=manager.beginTransaction();
		DetailFragment detailFragment=new DetailFragment();
		transaction.replace(R.id.detail, detailFragment,"detailFragment");
		transaction.addToBackStack("detailFragment");
		Bundle bundle=new Bundle();
		bundle.putString("item", item);
		detailFragment.setArguments(bundle);
		transaction.commit();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
}
