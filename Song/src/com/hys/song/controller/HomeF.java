package com.hys.song.controller;

import java.util.ArrayList;

import com.hys.song.R;
import com.hys.song.model.SGridViewAdapter;
import com.hys.song.view.AbOnItemClickListener;
import com.hys.song.view.AbSlidingPlayView;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class HomeF extends Fragment implements OnClickListener,
		OnItemClickListener {
	private TextView search;
	private ImageView scanf, messageRefresh;
	private GridView showAddress;
	private GridView hotmarket;
	private AbSlidingPlayView showAdv;
	private SGridViewAdapter addessAdapter;
	private SGridViewAdapter marketAdapter;
	private int[] addressData = { R.drawable.menu_guide_1,
			R.drawable.menu_guide_2, R.drawable.menu_guide_3,
			R.drawable.menu_guide_4, R.drawable.menu_guide_5,
			R.drawable.menu_guide_6, R.drawable.menu_guide_7,
			R.drawable.menu_guide_8 };
	private int[] hotmarketData = { R.drawable.menu_1, R.drawable.menu_2,
			R.drawable.menu_3, R.drawable.menu_4, R.drawable.menu_5,
			R.drawable.menu_6 };
	private ArrayList<View> advList;
	private int[] advIds = { R.drawable.show_m1, R.drawable.menu_viewpager_1,
			R.drawable.menu_viewpager_2, R.drawable.menu_viewpager_3,
			R.drawable.menu_viewpager_4, R.drawable.menu_viewpager_5 };
	


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.home_f, null);
		init(view);
		return view;
	}

	private void init(View view) {
		search = (TextView) view.findViewById(R.id.top_search);
		scanf = (ImageView) view.findViewById(R.id.scanf);
		messageRefresh = (ImageView) view.findViewById(R.id.message_refresh);
		search.setOnClickListener(this);
		scanf.setOnClickListener(this);
		messageRefresh.setOnClickListener(this);
		showAddress = (GridView) view.findViewById(R.id.show_address);
		hotmarket = (GridView) view.findViewById(R.id.hot_market);
		showAdv = (AbSlidingPlayView) view.findViewById(R.id.show_adv);
		addessAdapter = new SGridViewAdapter(getActivity(), addressData);
		marketAdapter = new SGridViewAdapter(getActivity(), hotmarketData);
		showAddress.setAdapter(addessAdapter);
		hotmarket.setAdapter(marketAdapter);
		showAddress.setOnItemClickListener(this);
		hotmarket.setOnItemClickListener(this);
		initBannerView();
	}

	private void initBannerView() {
		showAdv.setPlayType(1);
		showAdv.setSleepTime(3000);
		advList = new ArrayList<View>();
		for (int i = 0; i < advIds.length; i++) {
			View view = LayoutInflater.from(getActivity()).inflate(
					R.layout.banner_item_layout, null);
			ImageView imageView = (ImageView) view
					.findViewById(R.id.banner_item);
			imageView.setImageResource(advIds[i]);
			advList.add(view);
		}
		showAdv.addViews(advList);
		showAdv.startPlay();
		showAdv.setOnItemClickListener(new AbOnItemClickListener() {

			@Override
			public void onClick(int position) {
				Intent intent = new Intent(getActivity(), GodsDetail.class);
				startActivity(intent);

			}
		});

	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.scanf:
			intent.setClass(getActivity(), Scanf.class);
			break;
		case R.id.top_search:
			intent.setClass(getActivity(), Search.class);
			break;
		case R.id.message_refresh:
			intent.setClass(getActivity(), MessageRefresh.class);
			break;
		default:
			break;
		}
		startActivity(intent);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent();
		switch (parent.getId()) {
		case R.id.show_address:
			intent.setClass(getActivity(), Search.class);
			break;
		case R.id.hot_market:
			intent.setClass(getActivity(), GodsDetail.class);
			break;
		default:
			break;
		}
		startActivity(intent);

	}
}
