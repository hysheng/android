package com.hys.spinner;

import java.util.List;
import java.util.Map;

import com.hys.tools.AdapterTools;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Spinner spinner, spinner2, spinner3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		spinner = (Spinner) findViewById(R.id.spinner);
		// 方式一
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.planets_array,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new Lister());
		// 方式二
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, AdapterTools.getStrings());
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(adapter2);

		// 方式三
		spinner3 = (Spinner) findViewById(R.id.spinner3);
		List<Map<String, Object>> maps = AdapterTools.getMaps();
		SimpleAdapter adapter3 = new SimpleAdapter(this, maps, R.layout.item,
				new String[] { "Logo", "produce" }, new int[] { R.id.img,
						R.id.textView });
		spinner3.setAdapter(adapter3);

	}
}

class Lister implements OnItemSelectedListener {

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(
				parent.getContext(),
				"The planet is" + parent.getItemAtPosition(position).toString(),
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

}