package com.hys.song.view;

import android.content.Context;

import android.util.AttributeSet;
import android.widget.GridView;

public class MyGridView extends GridView {

	public MyGridView(Context context) {
		super(context);
	}
	public MyGridView(Context context,AttributeSet attrs) {
		super(context,attrs);
	}
	public MyGridView(Context context,AttributeSet attrs,int defStyle) {
		super(context,attrs,defStyle);
	}
	
	//重写这个方法，解决GridView在ScrollView的显示不全的问题
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
		
	}
	

}
