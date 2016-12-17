package com.hys.checkbox;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class Main extends Activity implements OnClickListener {
	
	private List<CheckBox> checkBoxs=new ArrayList<CheckBox>();
	
	@Override
	public void onClick(View v) {
		String s="";
		for(CheckBox checkBox:checkBoxs){
			if(checkBox.isChecked()){
				s+=checkBox.getText()+"\n";
			}
		}
		if("".equals(s)){
			s="You don't select any options!";
			
		}
		new AlertDialog.Builder(this).setMessage(s).
		setPositiveButton("close", null).show();
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
				super.onCreate(savedInstanceState);
		String[] checkboxText=new String[]{"Are you studeng?","Do you like Android?","Do you like tour?"};
		LinearLayout linearLayout=(LinearLayout)getLayoutInflater().inflate(R.layout.main, null);
		for(int i=0;i<checkboxText.length;i++){
			CheckBox checkBox=(CheckBox)getLayoutInflater().inflate(R.layout.checkbox, null);
			checkBoxs.add(checkBox);
			checkBoxs.get(i).setText(checkboxText[i]);
			linearLayout.addView(checkBox,i);
		}
		setContentView(linearLayout);
		Button button=(Button)findViewById(R.id.bt);
		button.setOnClickListener(this);
	}

}
