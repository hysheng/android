package com.hys.song.controller;

import com.hys.song.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ConfirmingOrder extends Activity implements OnClickListener{
	private Button submitOrder;
	private ImageButton orderBack,orderRefresh;
	private LinearLayout locationLayout,arrivedTimeLayout;
	private RelativeLayout payModeLayout,couponLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirming_order);	
		initView();
	}
	private void initView(){
		orderBack=(ImageButton)findViewById(R.id.order_back);
		orderRefresh=(ImageButton)findViewById(R.id.order_refresh);
		submitOrder=(Button)findViewById(R.id.order_submit);
		locationLayout=(LinearLayout)findViewById(R.id.order_location_layout);
		arrivedTimeLayout=(LinearLayout)findViewById(R.id.order_arrived_layout);
		payModeLayout=(RelativeLayout)findViewById(R.id.order_paym_layout);
		couponLayout=(RelativeLayout)findViewById(R.id.order_coupon_layout);
		submitOrder.setOnClickListener(this);
		orderBack.setOnClickListener(this);
		orderRefresh.setOnClickListener(this);
		locationLayout.setOnClickListener(this);
		arrivedTimeLayout.setOnClickListener(this);
		payModeLayout.setOnClickListener(this);
		couponLayout.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.order_back:
			finish();
			break;
		case R.id.order_refresh:
			onCreate(null);
			break;
		case R.id.order_submit:
			Intent intent=new Intent();
			intent.setClass(this, Pay.class);
			startActivity(intent);
			break;
		case R.id.order_location_layout:
			Toast.makeText(this, "选择接收地址", Toast.LENGTH_SHORT).show();
			break;
		case R.id.order_arrived_layout:
			Toast.makeText(this, "选择送达时间", Toast.LENGTH_SHORT).show();
			break;
		case R.id.order_paym_layout:
			Toast.makeText(this, "选择支付方式", Toast.LENGTH_SHORT).show();
			break;
		case R.id.order_coupon_layout:
			Toast.makeText(this, "选择商家代金券", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
	
}
