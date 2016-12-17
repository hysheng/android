package com.hys.song.controller;

import com.hys.song.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.view.View.OnClickListener;
public class SettingFrg extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.settings_f, null);
		init(view);
		return view;
	}
	private void init(View view){
		RelativeLayout accountManage=(RelativeLayout)view.findViewById(R.id.account_manage_menu);
		accountManage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "menu_manage", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
