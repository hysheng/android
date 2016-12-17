package com.hys.mylock;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SoudLock extends Activity {
	private Button gather;
	
	private Button stopgather;
	
	private MediaRecorder mediaRecorder;
	private MediaPlayer mediaPlayer;
	private AlertDialog alertDialog=null;
	private String path;
	private String paths;
	private ListView listView;
	private File saveFilePath;
	private String[] listFile;
	ShowRecordAdapter showRecordAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("声音样本的收集");
		setContentView(R.layout.soudlock);
		gather=(Button)findViewById(R.id.gather);
		stopgather=(Button)findViewById(R.id.stopgather);
		
		listView=(ListView)findViewById(R.id.list);
		mediaRecorder=new MediaRecorder();
		mediaPlayer=new MediaPlayer();
		mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			try{
				path=Environment.getExternalStorageDirectory().getCanonicalPath()+"/HughRecorder";
				File files=new File(path);
				if(!files.exists()){
					files.mkdir();
				}
				listFile=files.list();
			}catch(IOException exception){
				exception.printStackTrace();
			}
		}
		showRecordAdapter=new ShowRecordAdapter();
		gather.setOnClickListener(listener);
		stopgather.setOnClickListener(listener);
		
		if(listFile!=null){
			listView.setAdapter(showRecordAdapter);		
			}
		
		
	}
	private OnClickListener listener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			switch (v.getId()) {
			case R.id.gather:
				final EditText fileName=new EditText(SoudLock.this);
				final Builder builder=new Builder(SoudLock.this);
				builder.setTitle("请输入文件名").setView(fileName)
				.setPositiveButton("确定",new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int which) {
					String text=fileName.getText().toString();
					try{
						paths=path+"/"+text+new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis())+
								".amr";
						//paths=path+"/"+text+".amr";
						saveFilePath=new File(paths);
						mediaRecorder.setOutputFile(saveFilePath.getAbsolutePath());
						saveFilePath.createNewFile();
						mediaRecorder.prepare();
						mediaRecorder.start();
						gather.setText("正在录音...");
						gather.setEnabled(false);
						alertDialog.dismiss();
						File files=new File(paths);
						listFile=files.list();
						showRecordAdapter.notifyDataSetChanged();
						
					}catch (Exception e) {
						e.printStackTrace();
						
					}	
					}	
				});
				alertDialog=builder.create();
				alertDialog.setCanceledOnTouchOutside(false);
				alertDialog.show();
				
				break;
			
			case R.id.stopgather:
				 if (saveFilePath.exists() && saveFilePath != null) {
					    mediaRecorder.stop();
					    mediaRecorder.release();
					    new Builder(SoudLock.this)
					      .setTitle("是否保存该录音")
					      .setPositiveButton("确定", null)
					      .setNegativeButton("取消",
					        new DialogInterface.OnClickListener() {
					         @Override
					         public void onClick(DialogInterface dialog,
					           int which) {
					          saveFilePath.delete();
					          File files = new File(path);
					          listFile = files.list();
					          showRecordAdapter.notifyDataSetChanged();
					         }
					        }).show();
					   }
					   gather.setText("采集样本");
					   gather.setEnabled(true);
				break;
			default:
				break;
			}
			
		}
	};


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		android.os.Process.killProcess(android.os.Process.myPid());
	}
	
	class ShowRecordAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			if(listFile==null){
				return 0;
			}
			return listFile.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			View views=LayoutInflater.from(SoudLock.this).inflate(R.layout.list, null);
			TextView textView=(TextView)views.findViewById(R.id.filename);
			Button play=(Button)views.findViewById(R.id.play);
			Button stop=(Button)views.findViewById(R.id.stop);
			textView.setText(listFile[position]);
			play.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					try{
						mediaPlayer.reset();
						mediaPlayer.setDataSource(path+"/"+listFile[position]);
						if(!mediaPlayer.isPlaying()){
							mediaPlayer.prepare();
							mediaPlayer.start();
						}else {
							mediaPlayer.pause();
						}
					}catch(IOException e){
						e.printStackTrace();
					}
					
				}
			});
			stop.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(mediaPlayer.isPlaying()){
						mediaPlayer.stop();
					}
				}
			});
			return views;
		}
		
	}
	
}

