package com.hys.json;

import java.util.List;
import java.util.Map;

import com.hys.domain.Person;
import com.hys.tools.FastJsonTools;
import com.hys.tools.GsonTools;
import com.hys.tools.HttpTools;
import com.hys.tools.JsonTools;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private static final String TAG = "test";
	private Button person, persons, list_string, list_map;
	private String path, path_action;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.v(TAG, "father thread is====" + Thread.currentThread().getId());
		person = (Button) findViewById(R.id.person);
		persons = (Button) findViewById(R.id.persons);
		list_string = (Button) findViewById(R.id.list_string);
		list_map = (Button) findViewById(R.id.list_map);
		person.setOnClickListener(this);
		persons.setOnClickListener(this);
		list_string.setOnClickListener(this);
		list_map.setOnClickListener(this);
		path = "http://125.211.97.236:8080/Json/servlet/JsonAction?action_flag=";

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.person:
			path_action = path + "person";
			new Thread() {

				@Override
				public void run() {
					Log.v(TAG, "son thread ===="
							+ Thread.currentThread().getId());
					String js = HttpTools.getJsonContent(path_action);
					//Person person = JsonTools.toBePerson(js);
					//Person person = GsonTools.jsonToPerson(js, Person.class);
					Person person=FastJsonTools.jsonToPerson(js, Person.class);
					//Log.v(TAG, person.toString());
					Toast.makeText(MainActivity.this, person.toString(), Toast.LENGTH_LONG).show();
				}

			}.start();
			break;
		case R.id.persons:
			path_action = path + "persons";
			new Thread() {

				@Override
				public void run() {
//					List<Person> list = JsonTools.getPersons(HttpTools
//							.getJsonContent(path_action));
//					List<Person> list = GsonTools.jsonToListPerson(HttpTools
//							.getJsonContent(path_action), Person.class);
					List<Person> list=FastJsonTools.jsonToListPerson(HttpTools.getJsonContent(path_action), Person.class);
					//Log.v(TAG, list.toString());
					Toast.makeText(MainActivity.this, list.toString(), Toast.LENGTH_LONG).show();
				}
			}.start();
			break;
		case R.id.list_string:
			path_action = path + "list_string";
			new Thread() {

				@Override
				public void run() {
//					List<String> list_string = JsonTools
//							.toBeListString(HttpTools
//									.getJsonContent(path_action));
//					List<String> list_string = GsonTools.jsonToListString(HttpTools
//									.getJsonContent(path_action));
					List<String> list_string = FastJsonTools.jsonToListPerson(HttpTools
							.getJsonContent(path_action),String.class);
					//Log.v(TAG, list_string.toString());
					Toast.makeText(MainActivity.this, list_string.toString(), Toast.LENGTH_LONG).show();
				}
			}.start();

			break;
		case R.id.list_map:
			path_action = path + "list_map";
			new Thread() {

				@Override
				public void run() {
//					List<Map<String, Object>> list_map = JsonTools
//							.toListMap(HttpTools.getJsonContent(path_action));
//					List<Map<String, Object>> list_map = GsonTools
//							.jsonToListMap(HttpTools.getJsonContent(path_action));
					List<Map<String, Object>> list_map = FastJsonTools
							.jsonToListMap(HttpTools.getJsonContent(path_action));
					//Log.v(TAG, list_map.toString());
					Toast.makeText(MainActivity.this, list_map.toString(), Toast.LENGTH_LONG).show();
				}
			}.start();

			break;

		default:
			break;

		}

	}


}
