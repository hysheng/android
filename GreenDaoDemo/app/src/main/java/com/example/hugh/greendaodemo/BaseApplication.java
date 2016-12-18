package com.example.hugh.greendaodemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;



import hugh.greendao.*;

/**
 * Created by hugh on 16/9/29.
 */

public class BaseApplication extends Application {
    public DaoSession daoSession;
    public SQLiteDatabase db;
    public DaoMaster.DevOpenHelper helper;
    public DaoMaster daoMaster;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDatabase();
    }

    private void setupDatabase() {
        helper=new DaoMaster.DevOpenHelper(this, Constants.DB_NAME,null);
        db=helper.getWritableDatabase();
        daoMaster=new DaoMaster(db);
        daoSession=daoMaster.newSession();
    }
    public DaoSession getDaoSession(){
        return daoSession;
    }
    public SQLiteDatabase getDb(){
        return db;
    }
}
