package com.hys.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Fragment2 extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.f2, null);
		Button b2=(Button)view.findViewById(R.id.bt2);
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Fragment1 fragment1=(Fragment1) getFragmentManager()
						.findFragmentByTag("fragment1");
				EditText editText = (EditText) fragment1.getView()
						.findViewById(R.id.ed1);
				Toast.makeText(getActivity(), editText.getText(),
						Toast.LENGTH_SHORT).show();
				
			}
		});
		return view;
	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

}
