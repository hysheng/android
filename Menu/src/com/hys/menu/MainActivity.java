package com.hys.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {
	private Button menu;


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu=(Button)findViewById(R.id.menu);
        registerForContextMenu(menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(0, 1, 1, "开始");
        menu.add(0, 2, 2, "设置");
        menu.add(0, 3, 3, "退出");
        SubMenu subMenu=menu.addSubMenu(0, 4, 4, "省份");
        subMenu.add(0, 1, 1, "广东省");
        subMenu.add(0, 2, 2, "浙江省");
        menu.add(0, 5, 5, "账户");
        menu.add(0, 6, 6, "下载");
        menu.add(0, 7, 7, "帮助");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == 1) {
        	Intent intent=new Intent(this, Second.class);
        	startActivity(intent);
            
        }else {
			Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
			
		}
        return true;
       
    }

   


	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.setHeaderTitle("");
		menu.add(0,1,1,"copy");
		menu.add(0,1,2,"cut");
		super.onCreateContextMenu(menu, v, menuInfo);
	}


	@Override
	public boolean onContextItemSelected(MenuItem item) {
		Toast.makeText(this, item.getItemId(), Toast.LENGTH_SHORT).show();
		return true;
	}
	
    
}
