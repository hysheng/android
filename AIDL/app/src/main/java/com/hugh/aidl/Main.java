package com.hugh.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hugh.aidl.model.Person;
import com.hugh.aidl.model.PersonManager;

import java.util.List;

public class Main extends AppCompatActivity implements View.OnClickListener{
    private final String TAG="terminal";
    private EditText editText;
    private Button add,get;
    private boolean mBound=false;
    private List<Person> persons;
    private String name;
    private PersonManager personManager;
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        editText=(EditText)findViewById(R.id.edit);
        add=(Button)findViewById(R.id.add);
        get=(Button)findViewById(R.id.get);
        add.setOnClickListener(this);
        get.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                if(!mBound){
                    attemptToBindService();
                    Toast.makeText(this,"尝试连接。。。",Toast.LENGTH_SHORT).show();
                    return;
                }
               name=editText.getText().toString();
                person=new Person();
                person.setName(name);
                person.setAge(21);
                try {
                    personManager.addPerson(person);
                    Log.v(TAG,person.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.get:
                break;
        }
    }
    private void attemptToBindService(){
        Intent intent=new Intent();
        intent.setAction("com.hugh.aidl");
        //必须指定包名
        intent.setPackage("com.hugh.aidlserver");
        Log.v(TAG,"connecting...");
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }
    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.v(TAG,"service connected");
            personManager=PersonManager.Stub.asInterface(service);
            mBound=true;
            if(personManager!=null){
                try {
                    persons=personManager.getPersons();
                    Log.v(TAG, persons.toString());
                }catch (RemoteException e){
                    e.printStackTrace();
                }

            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.v(TAG,"service disconnected");
            mBound=false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        if(!mBound){
            attemptToBindService();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mBound){
            unbindService(serviceConnection);
        }
    }
}
