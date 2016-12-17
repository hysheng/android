package com.example.edittext;

import java.lang.reflect.Field;
import java.util.Random;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {

	private EditText num;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      num=(EditText)findViewById(R.id.num);
        Button check=(Button)findViewById(R.id.check);
        check.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String text=num.getText().toString();
				if(text==null||text.trim().equals("")){
					num.setError("输入有误，请重新输入");
				}
				
			}
		});
        /*EditText中输入表情
        final EditText editText=(EditText)findViewById(R.id.edit);
        Button add=(Button)findViewById(R.id.add);
        add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int randomId=1+new Random().nextInt(5);
				try {
					Field field=R.drawable.class.getDeclaredField("face"+randomId);
					int resouceId=Integer.parseInt(field.get(null).toString());
					Bitmap bitmap=BitmapFactory.decodeResource(getResources(), resouceId);
					ImageSpan imageSpan=new ImageSpan(MainActivity.this, bitmap);
					SpannableString spannableString=new SpannableString("face");
					spannableString.setSpan(imageSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					editText.append(spannableString);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		*/
    }

}
