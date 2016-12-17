package com.hys.song.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class MailLineView extends View {
	//颜色块的宽度
	private int colorWidth=5;
	//空白处的宽度
	private int emptyWidth=2;
	private Paint paint;
	public MailLineView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MailLineView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MailLineView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		int viewHeight=getHeight();
		//绘制完成的长度
		int drawLength=0;
		
		paint=new Paint();
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.FILL);
		int count=0;
		while(drawLength<getWidth()){
			drawLength+=emptyWidth*viewHeight;
			count++;
			if(count%2==1){
				paint.setColor(Color.rgb(255, 134, 134));
			}else{
				paint.setColor(Color.rgb(134, 194, 255));
			}
			Path path=new Path();
			path.moveTo(drawLength, viewHeight);
			path.lineTo(drawLength+colorWidth*viewHeight-viewHeight, viewHeight);
			path.lineTo(drawLength+colorWidth*viewHeight, 0);
			path.lineTo(drawLength+viewHeight, 0);
			path.close();
			canvas.drawPath(path, paint);
			drawLength+=colorWidth*viewHeight;
		}
	}
}
