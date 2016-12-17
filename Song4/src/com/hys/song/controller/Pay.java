package com.hys.song.controller;

import com.hys.song.R;

import android.R.integer;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Pay extends Activity implements OnClickListener {
	private ImageButton buyBack;
	private Button pay;
	private LinearLayout orderLayout, orderDetail, otherMode, otherPay, ali,
			wechat, qq;
	private ImageView spreadOrder;
	private int[] imgId = { R.id.pay_ali_select, R.id.pay_wechat_select,
			R.id.pay_qq_select };
	private ImageView[] imageViews;
	private TextView orderDescribe;
	private boolean flag = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pay);
		initView();
		initData();
	}

	private void initView() {
		buyBack = (ImageButton) findViewById(R.id.pay_back);
		pay = (Button) findViewById(R.id.to_pay);
		orderLayout = (LinearLayout) findViewById(R.id.pay_order_layout);
		orderDetail = (LinearLayout) findViewById(R.id.pay_order_detail);
		otherMode = (LinearLayout) findViewById(R.id.pay_other_mode);
		otherPay = (LinearLayout) findViewById(R.id.pay_other_layout);
		ali = (LinearLayout) findViewById(R.id.pay_alipay);
		wechat = (LinearLayout) findViewById(R.id.pay_wechat);
		qq = (LinearLayout) findViewById(R.id.pay_qq);
		spreadOrder = (ImageView) findViewById(R.id.pay_spread);
		imageViews = new ImageView[imgId.length];
		for (int i = 0; i < imgId.length; i++) {
			imageViews[i] = (ImageView) findViewById(imgId[i]);
		}
		updatePayState(0);
		spreadOrder = (ImageView) findViewById(R.id.pay_spread);
		orderDescribe = (TextView) findViewById(R.id.pay_order_describe);
		orderLayout.setOnClickListener(this);
		otherMode.setOnClickListener(this);
		ali.setOnClickListener(this);
		wechat.setOnClickListener(this);
		qq.setOnClickListener(this);
		buyBack.setOnClickListener(this);
		pay.setOnClickListener(this);

	}

	private void initData() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pay_back:
			finish();
			break;
		case R.id.to_pay:
			Toast.makeText(this, "打开支付宝支付", Toast.LENGTH_SHORT).show();
			break;
		case R.id.pay_order_layout:
			if (!flag) {
				orderDescribe.setVisibility(View.INVISIBLE);
				spreadOrder.setImageResource(R.drawable.merge);
				orderDetail.setVisibility(View.VISIBLE);
				flag=true;
			} else {
				orderDescribe.setVisibility(View.VISIBLE);
				spreadOrder.setImageResource(R.drawable.spread);
				orderDetail.setVisibility(View.GONE);
				flag=false;
			}
			break;
		case R.id.pay_other_mode:
			otherMode.setVisibility(View.GONE);
			otherPay.setVisibility(View.VISIBLE);
			break;
		case R.id.pay_alipay:
			updatePayState(0);
			break;
		case R.id.pay_wechat:
			updatePayState(1);
			break;
		case R.id.pay_qq:
			updatePayState(2);
			break;
		default:
			break;
		}

	}

	private void updatePayState(int position) {
		for (int i = 0; i < imageViews.length; i++) {
			if (i == position) {
				imageViews[i].setSelected(true);
			} else {
				imageViews[i].setSelected(false);
			}
		}
	}
}
