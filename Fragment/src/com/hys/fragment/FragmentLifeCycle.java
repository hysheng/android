package com.hys.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentLifeCycle extends Fragment {
	
	private final static String TAG="test";
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		Log.i(TAG, "---Fragment----->>>>>>>onAttach");
		super.onAttach(activity);
	}
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i(TAG, "---Fragment----->>>>>>>onCreate");
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i(TAG, "---Fragment----->>>>>>>onCreateView");
		View view=inflater.inflate(R.layout.demo, null);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i(TAG, "---Fragment----->>>>>>>onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		Log.i(TAG, "---Fragment----->>>>>>>onStart");
		super.onStart();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		Log.i(TAG, "---Fragment----->>>>>>>onResume");
		super.onResume();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		Log.i(TAG, "---Fragment----->>>>>>>onPause");
		super.onPause();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		Log.i(TAG, "---Fragment----->>>>>>>onStop");
		super.onStop();
	}
	
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		Log.i(TAG, "---Fragment----->>>>>>>onDestroyView");
		super.onDestroyView();
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i(TAG, "---Fragment----->>>>>>>onDestroy");
		super.onDestroy();
	}
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		Log.i(TAG, "---Fragment----->>>>>>>onDetach");
		super.onDetach();
	}
}
