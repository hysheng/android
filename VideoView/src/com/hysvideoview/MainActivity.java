package com.hysvideoview;

import java.io.File;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.VideoView;


public class MainActivity extends Activity implements MediaPlayerControl{
	private VideoView videoView;
	private MediaController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller=new MediaController(this);
        videoView=(VideoView)findViewById(R.id.videoView1);
        videoView.setMediaController(controller);
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
        	File dir=Environment.getExternalStorageDirectory();
        	File videoFile=new File(dir, "a.mp4");
        	Uri uri=Uri.fromFile(videoFile);
        	videoView.setVideoURI(uri);
        }
    }

	@Override
	public boolean canPause() {
		// TODO Auto-generated method stub
		return videoView.canPause();
	}

	@Override
	public boolean canSeekBackward() {
		// TODO Auto-generated method stub
		return videoView.canSeekBackward();
	}

	@Override
	public boolean canSeekForward() {
		// TODO Auto-generated method stub
		return videoView.canSeekForward();
	}

	@Override
	public int getAudioSessionId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBufferPercentage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCurrentPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDuration() {
		// TODO Auto-generated method stub
		return videoView.getDuration();
	}

	@Override
	public boolean isPlaying() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		if(!videoView.isPlaying()){
			videoView.pause();
		}
		
	}

	@Override
	public void seekTo(int pos) {
		// TODO Auto-generated method stub
		videoView.seekTo(pos);
		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		videoView.start();
		
	}

}
