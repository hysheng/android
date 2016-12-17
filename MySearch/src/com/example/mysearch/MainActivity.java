package com.example.mysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends Activity implements OnEditorActionListener,OnItemClickListener {
	private EditText searchInput;
	private ListView searchHistory;
	private Button deleteHistory;
	private ImageView deleteInput;
	private LinearLayout history;
	private ListView searchResult;

	private List<Map<String, Object>> resultData;
	private SimpleAdapter resultAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		searchInput = (EditText) findViewById(R.id.search_input);
		searchHistory = (ListView) findViewById(R.id.search_history);
		searchResult = (ListView) findViewById(R.id.search_result);
		deleteHistory = (Button) findViewById(R.id.delete_history);
		deleteInput = (ImageView) findViewById(R.id.delete_input);
		history = (LinearLayout) findViewById(R.id.history);
		String[] countries = getResources().getStringArray(R.array.countries);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.search_history_item, countries);
		searchHistory.setAdapter(adapter);
		searchResult.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this, GodsDetail.class);
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
			resultData = new ArrayList<Map<String, Object>>();

			for (int i = 4; i < 14; i++) {
				Map<String, Object> resultItem = new HashMap<String, Object>();
				resultItem.put("godsName", "iphone " + i);
				resultData.add(resultItem);
			}
			resultAdapter = new SimpleAdapter(this, resultData,
					R.layout.gods_item, new String[] { "godsName" },
					new int[] { R.id.gods_name });
			searchResult.setAdapter(resultAdapter);

		}

		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}

}
