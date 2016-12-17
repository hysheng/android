package com.hys.song.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hys.song.R;
import com.hys.song.java.service.GoodsService;

public class GoodsDetail extends Activity implements OnClickListener {

	private ListView evaluations;
	private SimpleAdapter evaluationAdapter;
	private List<Map<String, Object>> evaluationData;
	private ImageButton detailBack, detailRefresh,collect,addToCar;
	private Button buyNow;
	private ImageView goodsDetailImg;
	private GoodsService goodsService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_detail);
		goodsService=new GoodsService();
		initView();
		initData();
		
	}
	private void initView(){
		evaluations = (ListView) findViewById(R.id.evaluations);
		goodsDetailImg=(ImageView)findViewById(R.id.goods_detail_img);
		detailBack = (ImageButton) findViewById(R.id.detail_back);
		detailRefresh = (ImageButton) findViewById(R.id.detail_refresh);
		collect=(ImageButton)findViewById(R.id.menu_collect);
		addToCar=(ImageButton)findViewById(R.id.add_shoppingcar);
		buyNow=(Button)findViewById(R.id.buy_now);
		Glide.with(this).load(R.drawable.goods_detail_demo).into(goodsDetailImg);
		detailBack.setOnClickListener(this);
		detailRefresh.setOnClickListener(this);
		collect.setOnClickListener(this);
		addToCar.setOnClickListener(this);
		buyNow.setOnClickListener(this);
	}
	private void initData(){
		evaluationData = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 2; i++) {
			Map<String, Object> evaluation = new HashMap<String, Object>();
			evaluation.put("consumer_name", "黄家七嫂");
			evaluationData.add(evaluation);
		}
		evaluationAdapter = new SimpleAdapter(this, evaluationData,
				R.layout.evaluation_item, new String[] { "consumer_name" },
				new int[] { R.id.consumer_name });
		evaluations.setAdapter(evaluationAdapter);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.detail_back:
			finish();
			break;
		case R.id.detail_refresh:
			onCreate(null);
			break;
		case R.id.menu_collect:
			collect.setSelected(true);
			goodsService.collectGoods();
			Toast.makeText(this, "已收藏", Toast.LENGTH_SHORT).show();
			break;
		case R.id.add_shoppingcar:
			goodsService.addGoodsToShopinTrolley();
			Toast.makeText(this, "已添加到购物车中", Toast.LENGTH_SHORT).show();
			break;
		case R.id.buy_now:
			Intent intent=new Intent(this, ConfirmingOrder.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
