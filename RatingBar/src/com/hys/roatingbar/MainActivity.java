package com.hys.roatingbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Toast;


public class MainActivity extends Activity implements OnRatingBarChangeListener{
	
	private RatingBar ratingBar;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ratingBar=(RatingBar)findViewById(R.id.progress);
        ratingBar.setMax(100);
        ratingBar.setOnRatingBarChangeListener(this);
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
	public void onRatingChanged(RatingBar ratingBar, float rating,
			boolean fromUser) {
		int progress=ratingBar.getProgress();
		Toast.makeText(this, "progeress:"+progress+" "+rating, 2).show();
		
	}
}
