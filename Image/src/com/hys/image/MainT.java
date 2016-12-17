package com.hys.image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainT extends Activity implements OnSeekBarChangeListener {

	private ImageView imageView;
	private TextView textView, textView2;
	private Matrix matrix = new Matrix();
	private SeekBar seekBar, seekBar2;
	private int minWidth = 80;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maint);
		imageView = (ImageView) findViewById(R.id.yoona);
		textView = (TextView) findViewById(R.id.text1);
		textView2 = (TextView) findViewById(R.id.text2);
		seekBar = (SeekBar) findViewById(R.id.seekbar1);
		seekBar2 = (SeekBar) findViewById(R.id.seekbar2);
		seekBar.setOnSeekBarChangeListener(this);
		seekBar2.setOnSeekBarChangeListener(this);
		DisplayMetrics metrics = new DisplayMetrics();
		// 将当前窗口的一些信息放在metrics类中，
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		seekBar.setMax(metrics.widthPixels);

	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		if (seekBar.getId() == R.id.seekbar1) {
			int newWidth = progress;
			int newHeight = (int) (newWidth * 3 / 4);
			imageView.setLayoutParams(new LinearLayout.LayoutParams(newWidth,
					newHeight));
			textView.setText("图像宽度:" + newWidth + " 图像高度:" + newHeight);
		} else if (seekBar.getId() == R.id.seekbar2) {
			Bitmap bitmap = ((BitmapDrawable) (getResources()
					.getDrawable(R.drawable.material))).getBitmap();
			matrix.setRotate(progress);
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), matrix, true);
			imageView.setImageBitmap(bitmap);
			textView2.setText(progress + " 度");

		}

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

}
