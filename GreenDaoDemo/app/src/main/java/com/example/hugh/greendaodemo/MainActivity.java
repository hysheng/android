package com.example.hugh.greendaodemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import hugh.greendao.*;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView
        .OnItemClickListener {
    private Button add, search;
    private EditText editText;
    private ListView list;
    public static final String TAG = "test";
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = (Button) findViewById(R.id.add);
        search = (Button) findViewById(R.id.search);
        add.setOnClickListener(this);
        search.setOnClickListener(this);
        editText = (EditText) findViewById(R.id.editTextNode);
        list = (ListView) findViewById(R.id.list);
        String nameColumn = personDao.Properties.Name.columnName;
        String orderBy = nameColumn + " COLLATE LOCALIZED ASC ";
        cursor = getDb().query(getPersonDao().getTablename(), getPersonDao().getAllColumns(),
                null, null, null, null, orderBy);
        String[] from = {nameColumn, personDao.Properties.Sex.columnName};
        int[] to = {android.R.id.text1, android.R.id.text2};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout
                .simple_list_item_2, cursor, from, to);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                addPerson();
                break;
            case R.id.search:
                searchPerson();
                break;

        }
    }

    private void addPerson() {
        String personName = editText.getText().toString();
        editText.setText("");
        String sex = "famale";
        if (personName == null || personName.equals("")) {
            Toast.makeText(this, "Please enter a name to add", Toast.LENGTH_SHORT).show();
        } else {
            person personNote = new person(null, personName, 21, sex);
            getPersonDao().insert(personNote);
            Log.v(TAG, "Inserted new person,ID:" + personNote.getId());
            cursor.requery();
        }
    }

    private void searchPerson() {
        String name = editText.getText().toString();
        editText.setText("");
        if (name == null || name.equals("")) {
            Toast.makeText(this, "Please enter a name to query", Toast.LENGTH_SHORT).show();
        } else {
            Query query = getPersonDao().queryBuilder().where(personDao.Properties.Name.eq(name))
                    .orderAsc(personDao.Properties.Age).build();
            List persons = query.list();
            Toast.makeText(this, "There have" + persons.size(), Toast.LENGTH_SHORT).show();
        }
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    private personDao getPersonDao() {
        return ((BaseApplication) this.getApplicationContext()).getDaoSession().getPersonDao();
    }

    private SQLiteDatabase getDb() {
        return ((BaseApplication) this.getApplicationContext()).getDb();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        getPersonDao().deleteByKey(id);
        Toast.makeText(this,"Deleted person,ID:"+id,Toast.LENGTH_SHORT).show();
        Log.v(TAG,"Deleted person,ID:"+id);
        cursor.requery();
    }
}
