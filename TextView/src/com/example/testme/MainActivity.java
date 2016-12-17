package com.example.testme;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	public int getResourceId(String name){
		try {
			//������Դ��ID�ı��������Field�Ĵ��죬ʹ�÷��������ʵ�ֵ�
			Field field=R.drawable.class.getField(name);
			//ȡ�ò�������Դ��ID���ֶ�ֵ��ʹ�÷������
			return Integer.parseInt(field.get(null).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView textView1=(TextView)findViewById(R.id.action1);
		TextView textView2=(TextView)findViewById(R.id.action2);
		String t1="�������action1";
		String t2="�������action2";
		SpannableString spannableString=new SpannableString(t1);
		SpannableString spannableString2=new SpannableString(t2);
		spannableString.setSpan(new ClickableSpan() {
			
			@Override
			public void onClick(View widget) {
				Intent intent=new Intent(MainActivity.this,Action1.class);
				startActivity(intent);
				
			}
		}, 0, t1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		spannableString2.setSpan(new ClickableSpan() {
			
			@Override
			public void onClick(View widget) {
				Intent intent2=new Intent(MainActivity.this, Action2.class);
				startActivity(intent2);
			}
		}, 0, t2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		textView1.setText(spannableString);
		textView2.setText(spannableString2);
		textView1.setMovementMethod(LinkMovementMethod.getInstance());
		textView2.setMovementMethod(LinkMovementMethod.getInstance());
		/*TextView��ʾͼƬ�ͱ���
		TextView show=(TextView)findViewById(R.id.show);
		show.setTextColor(Color.BLACK);
		show.setBackgroundColor(Color.WHITE);
		show.setTextSize(20);
		String html="ͼ��1<img src='image1'/>ͼ��2<img src='image2'/>ͼ��3<img src='image3'/>";
		html+="ͼ��4<a href='http://www.baidu.com'><img src='image1'/></a>ͼ��5<img src='image5'/>";
		CharSequence charSequence=Html.fromHtml(html, new ImageGetter() {
			
			@Override
			public Drawable getDrawable(String source) {
				// TODO Auto-generated method stub
				//���ϵͳ��Դ����Ϣ������ͼƬ��Ϣ
				Drawable drawable=getResources().getDrawable(getResourceId(source));
				if(source.equals("image3")){
					drawable.setBounds(0, 0, drawable.getIntrinsicWidth()/2,
							drawable.getIntrinsicHeight()/2);
					
				}else {
					drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
							drawable.getIntrinsicHeight());
				}
				return drawable;
			}
		}, null);
		show.setText(charSequence);
		show.setMovementMethod(LinkMovementMethod.getInstance());
		
			 ��ʾ��ҳ��������ı� 
		private TextView show;
		private TextView show2;
		setContentView(R.layout.activity_main);
		show = (TextView) findViewById(R.id.show);
		show2 = (TextView) findViewById(R.id.show2);
		String html = "<font color='red'>Generation DT</font><br>";
		html += "<font color='000off'><big><i>Never give gup</i></big></font><p>";
		html += "<big><a href='www.apache.com'>������</a></big>";
		CharSequence charSequence = Html.fromHtml(html);
		show.setText(charSequence);
		show.setMovementMethod(LinkMovementMethod.getInstance());

		String text = "My URL:http://www.sina.com\n";
		text += "My email:hys0529hugh@163.com\n";
		text += "My number:888888";
		CharSequence charSequence2 = Html.fromHtml(text);
		show2.setText(charSequence2);
		show2.setMovementMethod(LinkMovementMethod.getInstance());
		*/

	}
}
