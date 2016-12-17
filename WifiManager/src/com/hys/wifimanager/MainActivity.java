package com.hys.wifimanager;


import java.util.List;
import android.widget.CompoundButton.OnCheckedChangeListener;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;


public class MainActivity extends Activity implements OnClickListener{
	
	private Button search;
	private Switch wifiSwitch;
	private TextView wifilist;
	private WifiManager manager;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager=(WifiManager) getSystemService(Context.WIFI_SERVICE);
        search=(Button)findViewById(R.id.serarch);
        wifiSwitch=(Switch)findViewById(R.id.wifiSwitch);
        wifilist=(TextView)findViewById(R.id.wifilsit);
        search.setOnClickListener(this);
        wifiSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				manager.setWifiEnabled(isChecked);
				
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


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		List<ScanResult> list=manager.getScanResults();
		for(ScanResult scanResult:list){
			wifilist.append(scanResult.SSID+"---пе╨ег©╤х:"+scanResult.level+"\n");
		}
		
	}
}
