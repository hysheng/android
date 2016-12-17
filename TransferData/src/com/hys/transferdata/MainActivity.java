package com.hys.transferdata;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.hys.domain.Person;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {
	
	private Button globalV,cutB;
	private MyApp myApp;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        globalV=(Button)findViewById(R.id.globalVariable);
        globalV.setOnClickListener(listener);
        cutB=(Button)findViewById(R.id.cutB);
        cutB.setOnClickListener(listener);
    }
    private OnClickListener listener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.globalVariable:
				myApp=(MyApp)getApplication();
				myApp.setName("Jessica");
				
				break;
			case R.id.cutB:
//				ClipboardManager clipboardManager=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
//				clipboardManager.setText("剪切吧");
				Person person=new Person("Jessica", 22);
				//将对象转换成字符串
				ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
				String base64String="";
				try {
					ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
					objectOutputStream.writeObject(person);
					base64String=Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ClipboardManager manager=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
				manager.setText(base64String);
				
				break;
			default:
				break;
			}
			Intent intent=new Intent(MainActivity.this, Show.class);
			startActivity(intent);
			
		}
	};

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
