package com.hys.song.controller;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.hys.song.R;

public class Main extends Activity implements OnClickListener {
	private ImageButton[] buttons;
	private int[] buttonIds = { R.id.settings_menu, R.id.home_menu,
			R.id.contact_menu };
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
		buttons = new ImageButton[buttonIds.length];
		for (int i = 0; i < buttonIds.length; i++) {
			buttons[i] = (ImageButton) findViewById(buttonIds[i]);
			buttons[i].setOnClickListener(this);
		}
		updateButtonState(1);
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
		// 设置Fragment的动画
		// transaction.setCustomAnimations(R.anim.cu_push_right_in,
		// R.anim.cu_push_right_out);
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
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.settings_menu:
			updateButtonState(0);
			if (settingFrg == null) {
				settingFrg = new SettingFrg();
				addFragment(settingFrg);
				showFragment(settingFrg);
			} else {
				if (settingFrg.isHidden()) {
					showFragment(settingFrg);
				}
			}
			break;
		case R.id.home_menu:
			updateButtonState(1);
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
			updateButtonState(2);
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
		default:
			break;
		}

	}

	// position为按钮数组的下标
	private void updateButtonState(int position) {
		for (int i = 0; i < buttons.length; i++) {
			if (i == position) {
				buttons[i].setSelected(true);
			} else {
				buttons[i].setSelected(false);
			}
		}
	}


}
