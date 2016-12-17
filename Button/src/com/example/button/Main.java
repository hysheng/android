package com.example.button;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.widget.Button;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo);
		Button my=(Button)findViewById(R.id.my);
		SpannableString spannableStringLeft=new SpannableString("left");
		Bitmap bitmapLeft=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		ImageSpan imageSpanLeft=new ImageSpan(bitmapLeft, DynamicDrawableSpan.ALIGN_BOTTOM);
		spannableStringLeft.setSpan(imageSpanLeft, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		SpannableString spannableStringRight=new SpannableString("right");
		Bitmap bitmapRight=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		ImageSpan imageSpanRight=new ImageSpan(bitmapRight, DynamicDrawableSpan.ALIGN_BOTTOM);
		spannableStringRight.setSpan(imageSpanRight, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		my.append(spannableStringLeft);
		my.append("My Button");
		my.append(spannableStringRight);
	}
	
}
