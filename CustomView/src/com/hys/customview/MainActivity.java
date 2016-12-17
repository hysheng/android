package com.hys.customview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);
		setContentView(new MyView(this));
	}

	class MyView extends View {
		Paint mPaint;

		public MyView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			mPaint = new Paint();
		}

		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.onDraw(canvas);
			// ���û�������ɫ
			canvas.drawColor(Color.GREEN);
			// д����
			mPaint.setColor(Color.BLACK);
			mPaint.setTextSize(40);
			mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);// �������
			canvas.drawText("Hello world!", 100, 50, mPaint);

			// ��ֱ��
			mPaint.setColor(Color.RED);
			mPaint.setStrokeWidth(4);
			canvas.drawLine(0, 0, 150, 150, mPaint);

			// ����һ������
			mPaint.setColor(Color.BLUE);
			canvas.drawRect(40, 100, 240, 200, mPaint);// ���ε��ĸ�������Ϊ(40,100),(40,200),(240,100),(240,200)

			// ������Բ
			mPaint.setColor(Color.YELLOW);
			canvas.drawOval(new RectF(300, 370, 120, 100), mPaint);// ���þ�����������Բ

			// ����һ�������
			mPaint.setColor(Color.BLUE);
			Path path = new Path();
			path.moveTo(300, 600 - 50);
			path.lineTo(300 + 50, 600 - 50);
			path.lineTo(300 + 70, 620 - 50);
			path.lineTo(300 + 90, 630 - 50);
			path.close();
			canvas.drawPath(path, mPaint);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
