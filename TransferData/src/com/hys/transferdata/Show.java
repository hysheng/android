package com.hys.transferdata;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import com.hys.domain.Person;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Base64;
import android.widget.TextView;

public class Show extends Activity {
	
	private TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);
//		MyApp myApp=(MyApp) getApplication();
		
		textView=(TextView)findViewById(R.id.show);
//		textView.setText(myApp.getName());
		ClipboardManager manager=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
		//textView.setText(manager.getText());
		String msg=manager.getText().toString();
		byte[] base64_byte=Base64.decode(msg, Base64.DEFAULT);
		ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(base64_byte);
		try {
			ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);
			Person person=(Person)objectInputStream.readObject();
			textView.setText(person.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
