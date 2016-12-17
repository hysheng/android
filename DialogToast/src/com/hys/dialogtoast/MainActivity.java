package com.hys.dialogtoast;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import com.hys.dialogtoast.CustomView.CallBack;;


public class MainActivity extends Activity {
	
	private Button dialog,toast;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog=(Button)findViewById(R.id.dialog);
        toast=(Button)findViewById(R.id.toast);
        dialog.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CustomView customView=new CustomView(MainActivity.this);
				customView.createDialog("提示", "你确定要删除它？", "确定", new CallBack() {
					
					@Override
					public void isConfirm(boolean flag) {
						// TODO Auto-generated method stub
						Log.i("test", ">>>>"+flag);
					}
				});
				
			}
		});
        toast.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CustomView customView=new CustomView(MainActivity.this);
				customView.createToast("远程主机拒绝访问!", getLayoutInflater());
			}
		});
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
