package com.hys.song.controller;

import com.hys.song.R;
import com.hys.song.model.GoodsSearchAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class Search extends Activity implements OnEditorActionListener,
		OnItemClickListener,OnClickListener{
	private EditText searchInput;
	private ListView searchHistory,searchResult;
	private Button deleteHistory;
	private ImageButton searchBack;
	private ImageView deleteInput;
	private LinearLayout history;
	private int[] goodsImages = { R.drawable.hot_good_1, R.drawable.hot_good_2,
			R.drawable.hot_good_3, R.drawable.hot_good_4,
			R.drawable.hot_good_5, R.drawable.hot_good_6, R.drawable.adv_1_3 };
	private String[] goodsNames;
	private String[] goodsPrices;
	private String[] goodsVolume;
	private GoodsSearchAdapter resultAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		initView();
		initData();	
	}
	private void initView(){
		searchInput = (EditText) findViewById(R.id.search_input);
		searchHistory = (ListView) findViewById(R.id.search_history);
		searchResult = (ListView) findViewById(R.id.search_result);
		searchBack=(ImageButton)findViewById(R.id.search_back);
		deleteHistory = (Button) findViewById(R.id.delete_history);
		searchBack=(ImageButton)findViewById(R.id.search_back);
		deleteInput = (ImageView) findViewById(R.id.delete_input);
		history = (LinearLayout) findViewById(R.id.history);
	}
	private void initData(){
		String[] countries = getResources().getStringArray(
				R.array.search_history_demo);
		goodsNames = getResources().getStringArray(R.array.goods_name_demo);
		goodsPrices = getResources().getStringArray(R.array.goods_prices_demo);
		goodsVolume = getResources().getStringArray(R.array.goods_volume_demo);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.search_history_item, countries);
		searchHistory.setAdapter(adapter);
		searchBack.setOnClickListener(this);
		deleteHistory.setOnClickListener(this);
		deleteInput.setOnClickListener(this);
		searchResult.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Search.this, GoodsDetail.class);
				startActivity(intent);
			}
		});
		searchInput.addTextChangedListener(new SearchTextListener());
		searchInput.setOnEditorActionListener(this);
	}
	class SearchTextListener implements TextWatcher {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			if (!s.toString().equals("")) {
				deleteInput.setVisibility(View.VISIBLE);
			} else {
				deleteInput.setVisibility(View.GONE);
			}

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}

	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		// TODO Auto-generated method stub
		if (actionId == EditorInfo.IME_ACTION_SEARCH) {
			((InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE))
					.hideSoftInputFromWindow(this.getCurrentFocus()
							.getWindowToken(),
							InputMethodManager.HIDE_NOT_ALWAYS);
			history.setVisibility(View.GONE);
			resultAdapter = new GoodsSearchAdapter(Search.this, goodsImages,
					goodsNames, goodsPrices, goodsVolume);
			searchResult.setAdapter(resultAdapter);

		}

		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.search_back:
			finish();
			break;
		case R.id.delete_input:
			break;
		case R.id.delete_history:
			break;
		default:
			break;
		}
		
	}

}