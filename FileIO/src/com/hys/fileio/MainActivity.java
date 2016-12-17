package com.hys.fileio;

import java.io.InputStream;
import java.io.OutputStream;

import com.hys.service.FileService;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;


public class MainActivity extends Activity {

	protected static final String TAG = "test";
	private EditText editTextInfo;
	private EditText editName;
	private Button saveButton;
	private TextView tView;
	private String fname;
	private String inf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.i(TAG, "oncreate");
       
        editName=(EditText)findViewById(R.id.ed1);
        editTextInfo=(EditText)findViewById(R.id.ed2);
       
        saveButton=(Button)findViewById(R.id.save);
        tView=(TextView)findViewById(R.id.show);
        saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			FileService service=new FileService();
			OutputStream outputStream;
			InputStream inputStream;
			 fname=editName.getText().toString();
		        Log.i(TAG, "----"+fname);
		        
		        inf=editTextInfo.getText().toString();
		        Log.i(TAG,  "----"+inf);
			Log.i(TAG, "ÕýÔÚ±£´æ");
			
			try {
				outputStream = MainActivity.this.openFileOutput(fname,Context.MODE_PRIVATE);
				service.save(outputStream, inf);
				Toast.makeText(MainActivity.this, "Save successed", Toast.LENGTH_LONG).show();
				inputStream=MainActivity.this.openFileInput(fname);
				tView.setText(service.read(inputStream));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
				
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
}
