package com.hys.song.controller;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageButton;

import com.hys.song.R;

public class Main extends Activity implements OnClickListener,
		OnItemSelectedListener {
	private ImageButton[] buttons = new ImageButton[3];
	private int[] buttonIds = { R.id.home_menu, R.id.contact_menu,
			R.id.settings_menu };
	private HomeF homeFrg;
	private ContactFrg contactFrg;
	private SettingFrg settingFrg;
	private FragmentManager fragmentManager;
	private FragmentTransaction transaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		fragmentManager = getFragmentManager();
		initView();
		

	}

	private void initView() {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = (ImageButton) findViewById(buttonIds[i]);
			buttons[i].setOnClickListener(this);
		}

		if (homeFrg == null) {
			homeFrg = new HomeF();
			addFragment(homeFrg);
			showFragment(homeFrg);
		} else {
			showFragment(homeFrg);
		}
	}

	private void addFragment(Fragment fragment) {
		transaction = fragmentManager.beginTransaction();
		transaction.add(R.id.show, fragment);
		transaction.commit();
	}

	private void removeFragment(Fragment fragment) {
		transaction = fragmentManager.beginTransaction();
		transaction.remove(fragment);
		transaction.commit();

	}

	private void showFragment(Fragment fragment) {
		transaction = fragmentManager.beginTransaction();
//		ÓÐÎÊÌâ
//		transaction.setCustomAnimations(R.anim.cu_push_right_in,
//				R.anim.cu_push_right_out);
		if (homeFrg != null) {
			transaction.hide(homeFrg);
		}
		if (contactFrg != null) {
			transaction.hide(contactFrg);
		}
		if (settingFrg != null) {
			transaction.hide(settingFrg);
		}
		transaction.show(fragment);
		transaction.commitAllowingStateLoss();
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.home_menu:
			if (homeFrg == null) {
				homeFrg = new HomeF();
				addFragment(homeFrg);
				showFragment(homeFrg);
			} else {
				if (homeFrg.isHidden()) {
					showFragment(homeFrg);
				}
			}
			break;
		case R.id.contact_menu:
			if (contactFrg == null) {
				contactFrg = new ContactFrg();
				addFragment(contactFrg);
				showFragment(contactFrg);
			} else {
				if (contactFrg.isHidden()) {
					showFragment(contactFrg);
				}
			}
			break;
		case R.id.settings_menu:
			if (settingFrg == null) {
				settingFrg = new SettingFrg();
				addFragment(settingFrg);
				showFragment(settingFrg);
			} else {
				if (settingFrg.isHidden()) {
					showFragment(settingFrg);
				}
			}
		default:
			break;
		}

	}
}
