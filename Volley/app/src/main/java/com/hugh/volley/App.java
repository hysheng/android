package com.hugh.volley;

import android.app.Application;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.RequestQueue;

/**
 * Created by hugh on 16/9/27.
 */

public class App extends Application {
    private static RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.initialize(this);
    }
    public static RequestQueue initOnHttp(){
        requestQueue=NoHttp.newRequestQueue();
        return requestQueue;
    }
}
