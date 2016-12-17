package com.hys.autocomplete;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;


public class MainActivity extends Activity {
	private static final String[] CON={"an","animal","ability","all","abandon","are"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoCompleteTextView textView=(AutoCompleteTextView)findViewById(R.id.autocompelete);
        MultiAutoCompleteTextView mul=(MultiAutoCompleteTextView)findViewById(R.id.mul);
        ArrayAdapter<CharSequence> adapter=new ArrayAdapter<CharSequence>(this, R.layout.list_item, CON);
        textView.setAdapter(adapter);
        //多选提示框，选项之间用逗号隔开
        mul.setAdapter(adapter);
        mul.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        
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
