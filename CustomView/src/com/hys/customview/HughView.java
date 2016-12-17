package com.hys.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class HughView extends View {
	
	private Paint paint;
	private static final String title="2006-2009�ϰ�����������";
	private static final String content="���Թ�˾���۵�ͳ������";
	
	public HughView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
	}
	//���õ�xml�ļ��б���Ҫ��������췽��
	public HughView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		paint=new Paint();
		paint.setColor(Color.BLACK);
		paint.setTextSize(18);
		canvas.drawText(title, 20, 20, paint);
		
		canvas.drawLine(50, 100, 50, 500,paint);
		canvas.drawLine(50, 500, 400, 500, paint);
		int[] array = { 0, 50, 100, 150, 200, 250, 300, 350 };
		paint.setTextSize(10);// ���������С
		canvas.drawText("��λ����Ԫ", 20, 90, paint);
		for (int i = 0; i < array.length; i++) {
			canvas.drawLine(50, 500 - array[i], 54, 500 - array[i], paint);
			canvas.drawText(array[i] + "", 20, 500 - array[i], paint);
		}
		String[] array2 = { "2008��", "2009��", "2010��", "2011�ϰ���" };
		for (int i = 0; i < array2.length; i++) {
			canvas.drawText(array2[i], array[i] + 80, 520, paint);
		}
		paint.setColor(Color.BLUE);
		paint.setStyle(Style.FILL);
		canvas.drawRect(new Rect(90, 500 - 56, 110, 500), paint);
		canvas.drawRect(new Rect(140, 500 - 98, 160, 500), paint);
		canvas.drawRect(new Rect(190, 500 - 207, 210, 500), paint);
		canvas.drawRect(new Rect(240, 500 - 318, 260, 500), paint);
		paint.setColor(Color.BLACK);
		canvas.drawText("56.32", 88, 500 - 58, paint);
		canvas.drawText("90.00", 138, 500 - 100, paint);
		canvas.drawText("207.67", 188, 500 - 209, paint);
		canvas.drawText("318.56", 238, 500 - 320, paint);
		paint.setColor(Color.BLACK);
		paint.setTextSize(16);
		canvas.drawText(content, 20, 560, paint);
		
		
		
		
	}

}
