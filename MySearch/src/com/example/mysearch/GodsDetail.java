package com.example.mysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class GodsDetail extends Activity {

	private ListView evaluations;
	private SimpleAdapter evaluationAdapter;
	private List<Map<String, Object>> evaluationData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gods_detail);
		evaluations = (ListView) findViewById(R.id.evaluations);
		evaluationData = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 7; i++) {
			Map<String, Object> evaluation = new HashMap<String, Object>();
			evaluation.put("consumer_name", "»Æ¼ÒÆßÉ©");
			evaluationData.add(evaluation);
		}
		evaluationAdapter = new SimpleAdapter(this, evaluationData,
				R.layout.evaluation_item, new String[] { "consumer_name" },
				new int[] { R.id.consumer_name });
		evaluations.setAdapter(evaluationAdapter);
	}
}
