package com.hys.volleydemo;

import com.hys.presenter.NetworkService;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;


public class MainActivity extends Activity {
	private Button getData;
	private ImageView img;
	private String url="http://172.18.5.42:8080/Json/servlet/JsonAction?action_flag=person";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData=(Button)findViewById(R.id.get);
        img=(ImageView)findViewById(R.id.img);
        getData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				NetworkService.sendStringRequest(url);
//				NetworkService.sendJsonArrayRequest(url);
				NetworkService.sendImageRequest(img);
				
			}
		});
    }

}
