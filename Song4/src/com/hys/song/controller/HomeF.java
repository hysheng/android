package com.hys.song.controller;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hys.library.zxing.android.CaptureActivity;
import com.hys.song.R;
import com.hys.song.model.AbOnItemClickListener;
import com.hys.song.model.BottemAdvGridViewAdapter;
import com.hys.song.model.SGridViewAdapter;
import com.hys.song.view.AbSlidingPlayView;

public class HomeF extends Fragment implements OnClickListener,
		OnItemClickListener {
	private TextView search;
	private ImageView scanf, messageRefresh;
	private GridView showAddress, hotmarket;
	private AbSlidingPlayView showAdv;
	private SGridViewAdapter addessAdapter;
	private BottemAdvGridViewAdapter marketAdapter;
	private int[] addressData = { R.drawable.menu_guide_1,
			R.drawable.menu_guide_2, R.drawable.menu_guide_3,
			R.drawable.menu_guide_4, R.drawable.menu_guide_5,
			R.drawable.menu_guide_6, R.drawable.menu_guide_7,
			R.drawable.menu_guide_8 };
	private int[] hotmarketData = { R.drawable.hot_good_1,
			R.drawable.hot_good_2, R.drawable.hot_good_3,
			R.drawable.hot_good_4, R.drawable.hot_good_5 ,R.drawable.hot_good_6};
	private ArrayList<View> advList;
	private int[] bannerIds = { R.drawable.banner_1, R.drawable.banner_2,
			R.drawable.banner_3, R.drawable.banner_4, R.drawable.banner_5,
			R.drawable.banner_6 };
	private int[] middleAdvs = { R.drawable.adv_1_1, R.drawable.adv_1_2,
			R.drawable.adv_1_3, R.drawable.adv_1_4, R.drawable.adv_2_1,
			R.drawable.adv_2_2, R.drawable.adv_2_3, R.drawable.adv_2_4 };
	private int[] middleIds = { R.id.adv_1_0, R.id.adv_1_1, R.id.adv_1_2,
			R.id.adv_1_3, R.id.adv_2_1, R.id.adv_2_2, R.id.adv_2_3,
			R.id.adv_2_4 };
	private ImageView[] middleViews;

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
		initData();
		initView(view);
		return view;
	}

	private void initData() {

	}

	private void initView(View view) {
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
		marketAdapter = new BottemAdvGridViewAdapter(getActivity(),
				hotmarketData);
		showAddress.setAdapter(addessAdapter);
		hotmarket.setAdapter(marketAdapter);
		showAddress.setOnItemClickListener(this);
		hotmarket.setOnItemClickListener(this);
		middleViews = new ImageView[middleAdvs.length];
		for (int i = 0; i < middleAdvs.length; i++) {
			middleViews[i] = (ImageView) view.findViewById(middleIds[i]);
			Glide.with(getActivity()).load(middleAdvs[i]).into(middleViews[i]);
			middleViews[i].setOnClickListener(this);
		}
		initBannerView();
	}

	private void initBannerView() {
		showAdv.setPlayType(1);
		showAdv.setSleepTime(3000);
		// 获取显示屏的大小
		Point point = new Point();
		getActivity().getWindowManager().getDefaultDisplay().getSize(point);
		advList = new ArrayList<View>();
		for (int i = 0; i < bannerIds.length; i++) {
			View view = LayoutInflater.from(getActivity()).inflate(
					R.layout.banner_item_layout, null);
			ImageView imageView = (ImageView) view
					.findViewById(R.id.banner_item);
			// imageView.setImageResource(advIds[i]);
			Glide.with(getActivity()).load(bannerIds[i]).into(imageView);
			advList.add(view);
		}
		showAdv.addViews(advList);
		showAdv.startPlay();
		showAdv.setOnItemClickListener(new AbOnItemClickListener() {

			@Override
			public void onClick(int position) {
				Intent intent = new Intent(getActivity(), GoodsDetail.class);
				startActivity(intent);

			}
		});

	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.scanf:
			intent.setClass(getActivity(), CaptureActivity.class);
			break;
		case R.id.top_search:
			intent.setClass(getActivity(), Search.class);
			break;
		case R.id.message_refresh:
			intent.setClass(getActivity(), MessageRefresh.class);
			break;
		case R.id.adv_1_0:
		case R.id.adv_1_1:
		case R.id.adv_1_2:
		case R.id.adv_1_3:
		case R.id.adv_2_1:
		case R.id.adv_2_2:
		case R.id.adv_2_3:
		case R.id.adv_2_4:
			intent.setClass(getActivity(), GoodsDetail.class);
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
			intent.setClass(getActivity(), GoodsDetail.class);
			break;
		default:
			break;
		}
		startActivity(intent);

	}


}
