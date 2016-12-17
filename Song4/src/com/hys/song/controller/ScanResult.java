package com.hys.song.controller;

import com.google.zxing.WriterException;
import com.hys.song.R;
import com.hys.library.zxing.encode.CodeCreator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ScanResult extends Activity {
	private static final String DECODED_CONTENT_KEY = "codedContent";
	private static final String DECODED_BITMAP_KEY = "codedBitmap";
	private TextView qrCoded;
	private ImageView qrCodeImage;
	private Button creator, scanner;
	private EditText qrCodeUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scaf_result);
		qrCoded = (TextView) findViewById(R.id.ECoder_title);
		qrCodeImage = (ImageView) findViewById(R.id.ECoder_image);
		creator = (Button) findViewById(R.id.ECoder_creator);
		scanner = (Button) findViewById(R.id.ECoder_scaning);
		qrCodeUrl = (EditText) findViewById(R.id.ECoder_input);

		creator.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				String url = qrCodeUrl.getText().toString();
				try {
					Bitmap bitmap = CodeCreator.createQRCode(url);
					qrCodeImage.setImageBitmap(bitmap);
				} catch (WriterException e) {
					e.printStackTrace();
				}

			}
		});
		Intent data=getIntent();
		Bundle bundle=data.getExtras();
		if(bundle!=null){
			String content = data.getStringExtra(DECODED_CONTENT_KEY);
			Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);

			qrCoded.setText("解码结果： \n" + content);
			qrCodeImage.setImageBitmap(bitmap);
		}

	}
}
