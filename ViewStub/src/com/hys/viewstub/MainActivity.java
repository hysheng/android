package com.hys.viewstub;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;


public class MainActivity extends Activity implements OnClickListener{
	
	private ViewStub viewStub;
	private Button show,hode;
	private LinearLayout layout;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewStub=(ViewStub)findViewById(R.id.viewstub);
        show=(Button)findViewById(R.id.show);
        hode=(Button)findViewById(R.id.hode);
        show.setOnClickListener(this);
        hode.setOnClickListener(this);
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
		
		if(v.getId()==R.id.show){
			if(layout==null){
			layout=(LinearLayout)viewStub.inflate();
			Button button=(Button)layout.findViewById(R.id.button1);
			button.setText("动态加载的View控件");
			}else{
				viewStub.setVisibility(ViewStub.VISIBLE);
			}
		}else {
			viewStub.setVisibility(ViewStub.GONE);
		}
		
	}
}
