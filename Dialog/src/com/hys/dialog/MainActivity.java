package com.hys.dialog;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private final static int MAX = 100;
	private final static int SIMPLE = 1;
	private final static int LIST = 2;
	private final static int SINGLE = 3;
	private final static int PROGRESS = 4;
	private final static int CUSTOM = 5;
	private Button simple;
	private Button list;
	private Button single;
	private Button progress;
	private Button custom;
	private AlertDialog alertDialog;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		simple = (Button) findViewById(R.id.simpleAlertDialog);
		list = (Button) findViewById(R.id.listeAlertDialog);
		single = (Button) findViewById(R.id.singleChoiceAlertDialog);
		progress = (Button) findViewById(R.id.progressAlertDialog);
		custom = (Button) findViewById(R.id.customAlertDialog);
		simple.setOnClickListener(listener);
		list.setOnClickListener(listener);
		single.setOnClickListener(listener);
		progress.setOnClickListener(listener);
		custom.setOnClickListener(listener);

	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.simpleAlertDialog:
				showDialog(SIMPLE);
				break;
			case R.id.listeAlertDialog:
				showDialog(LIST);
				break;
			case R.id.singleChoiceAlertDialog:
				showDialog(SINGLE);
				break;
			case R.id.progressAlertDialog:
				showDialog(PROGRESS);
				break;
			case R.id.customAlertDialog:
				showDialog(CUSTOM);
				break;
			}
		}
	};

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		Log.i("test", "Dialog������");
		AlertDialog.Builder builder=new Builder(this);
		final CharSequence[] items={"��ɫ","��ɫ","��ɫ"};
		switch (id) {
		case SIMPLE:
			builder.setMessage("��ȷ��Ҫ�˳������?");
			builder.setPositiveButton("��", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					MainActivity.this.finish();
					
				}
			});
			builder.setNegativeButton("��", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
					
				}
			});
			return builder.create();
		case LIST:
			builder.setTitle("��ѡ��һ����ɫ?");
			builder.setItems(items, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();
					
				}
			});
			return builder.create();
		case SINGLE:
			builder.setTitle("��ѡ��һ����ɫ?");
			builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();
					if(alertDialog!=null){
						alertDialog.dismiss();
					}
				}
			});
			return alertDialog;
			
		case PROGRESS:
			progressDialog=new ProgressDialog(this);
			progressDialog.setTitle("������");
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.setMax(MAX);
			progressDialog.setButton(DialogInterface.BUTTON_POSITIVE,"ȷ��", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"ȡ��", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			return progressDialog;
			case CUSTOM:
				LayoutInflater inflater=LayoutInflater.from(this);
				View view=inflater.inflate(R.layout.custom, null);
				builder.setIcon(R.drawable.qq);
				builder.setTitle("�Զ���");
				builder.setView(view);
				builder.setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
				builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
				return builder.create();
				default:
					return null;
		}
		
		
	}

}
