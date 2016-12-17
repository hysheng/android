package com.hys.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity implements OnClickListener {

	private Button qq, wechat, sina, momo, twitter;
	private FragmentManager manager;
	private FragmentTransaction transaction;
	private Fragment fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("test", "$$$$$$$$$$$$$$$$$");
		setContentView(R.layout.main);
		qq=(Button)findViewById(R.id.qq);
		wechat=(Button)findViewById(R.id.wechat);
		sina=(Button)findViewById(R.id.sina);
		momo=(Button)findViewById(R.id.momo);
		twitter=(Button)findViewById(R.id.twitter);
		manager=getFragmentManager();
		qq.setOnClickListener(this);
		wechat.setOnClickListener(this);
		sina.setOnClickListener(this);
		momo.setOnClickListener(this);
		twitter.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		transaction=manager.beginTransaction();
		switch (v.getId()) {
		case R.id.qq:
			fragment=new QQFragment();
			transaction.replace(R.id.main, fragment, "qq");
			transaction.addToBackStack("qq");
			break;
		case R.id.wechat:
			fragment=new WeChatFragment();
			transaction.replace(R.id.main, fragment,"wechat");
			transaction.addToBackStack("wechat");
			break;
		case R.id.sina:
			fragment=new SinaFragment();
			transaction.replace(R.id.main, fragment,"sina");
			transaction.addToBackStack("sina");
			break;
		case R.id.momo:
			fragment=new MoMoFragment();
			transaction.replace(R.id.main, fragment,"momo");
			transaction.addToBackStack("momo");
			break;
		case R.id.twitter:
			fragment=new TwitterFragment();
			transaction.replace(R.id.main, fragment,"twitter");
			transaction.addToBackStack("twitter");
			break;

		default:
			break;
		}
		transaction.commit();
	}

}
