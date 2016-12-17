package com.hys.tabwidget;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Resources resources=getResources();
        TabHost tabHost=getTabHost();
        TabSpec spc;
        Intent intent;
        
        intent=new Intent().setClass(this, Photo.class);
        spc=tabHost.newTabSpec("photo").setIndicator("hphoto").setContent(intent);
        tabHost.addTab(spc);
        
        intent.setClass(this, Content.class);
        spc=tabHost.newTabSpec("content").setIndicator("Content").setContent(intent);
        tabHost.addTab(spc);
        
        intent.setClass(this,History.class);
        spc=tabHost.newTabSpec("history").setIndicator("History").setContent(intent);
        tabHost.addTab(spc);
        
        intent.setClass(this, Test.class);
        spc=tabHost.newTabSpec("test").setIndicator("Test").setContent(intent);
        tabHost.addTab(spc);
        
        tabHost.setCurrentTab(1);
        
    }
}
