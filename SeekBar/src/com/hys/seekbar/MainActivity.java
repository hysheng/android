package com.hys.seekbar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;


public class MainActivity extends Activity implements OnSeekBarChangeListener{
	
	private TextView textView;
	private SeekBar seekBar1,seekBar2;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textView);
        seekBar1=(SeekBar)findViewById(R.id.seekBar1);
        seekBar2=(SeekBar)findViewById(R.id.seekBar2);
        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);
    }

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		if(seekBar.getId()==R.id.seekBar1){
			textView.setText("SeekBar1�Ľ���Ϊ:"+progress);
		}else {
			textView.setText("SeekBar2�Ľ���Ϊ:"+progress);
		}
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		if(seekBar.getId()==R.id.seekBar1){
			textView.setText("SeekBar1���϶�");
		}else {
			textView.setText("SeekBar2���϶�");
		}
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		if(seekBar.getId()==R.id.seekBar1){
			textView.setText("SeekBar1ֹͣ�϶�");
		}else {
			textView.setText("SeekBar2ֹͣ�϶�");
		}
		
	}

}
