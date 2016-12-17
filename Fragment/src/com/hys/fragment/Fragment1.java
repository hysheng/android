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

public class Fragment1 extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.f1, null);
		Button b1 = (Button) view.findViewById(R.id.bt1);
		b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment2 fragment2=(Fragment2) getFragmentManager()
						.findFragmentByTag("fragment2");
				EditText editText = (EditText) fragment2.getView()
						.findViewById(R.id.ed2);
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
