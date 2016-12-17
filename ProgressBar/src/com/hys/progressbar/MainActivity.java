package com.hys.progressbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity implements OnClickListener {

	private Button add, reduce;
	private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置窗口有刻度的效果
		requestWindowFeature(Window.FEATURE_PROGRESS);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setProgressBarVisibility(true);
		setProgressBarIndeterminate(true);
		setProgress(3500);
		setContentView(R.layout.activity_main);
		progressBar = (ProgressBar) findViewById(R.id.progress);
		add = (Button) findViewById(R.id.add);
		reduce = (Button) findViewById(R.id.reduce);
		add.setOnClickListener(this);
		reduce.setOnClickListener(this);

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
		if (v.getId() == R.id.add) {
			progressBar.setProgress((int) (progressBar.getProgress() * 1.2));
			progressBar.setSecondaryProgress((int) (progressBar
					.getSecondaryProgress() * 1.2));
		} else if (v.getId() == R.id.reduce) {
			progressBar.setProgress((int) (progressBar.getProgress() * 0.8));
			progressBar.setSecondaryProgress((int) (progressBar
					.getSecondaryProgress() * 0.8));

		}
	}
}
