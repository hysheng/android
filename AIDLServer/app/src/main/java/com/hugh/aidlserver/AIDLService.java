package com.hugh.aidlserver;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hugh.aidl.model.Person;
import com.hugh.aidl.model.PersonManager;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by hugh on 16/10/2.
 */

public class AIDLService extends Service {
    private final String TAG = "server";
    private List<Person> persons = new ArrayList<>();

    private final PersonManager.Stub personManager = new PersonManager.Stub() {

        @Override
        public List<Person> getPersons() throws RemoteException {
            synchronized (this){
                Log.v(TAG,"invoking getPersons() method ,now the list is: "+persons.toString());
                if(persons!=null){
                    return persons;
                }
                return new ArrayList<>();
            }

        }

        @Override
        public void addPerson(Person person) throws RemoteException {
            synchronized (this){
                if(persons==null){
                    persons=new ArrayList<Person>();
                }
                if(person==null){
                    Log.v(TAG,"person is null in In");
                    person=new Person();
                }
                person.setAge(18);
                if(!persons.contains(person)){
                    persons.add(person);
                }
                Log.v(TAG,"invoking addPerson() method ,now the list is :"+persons.toString());
            }
        }
    };

    @Override
    public void onCreate() {
        Person person=new Person();
        person.setName("Jessica");
        person.setAge(22);
        persons.add(person);
        Log.v(TAG,"server's service oncreate");
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG,"on bind,intent="+intent.toString());
        return personManager;
    }
}
