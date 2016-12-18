package com.hugh.volley;



import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.download.DownloadRequest;
import com.yolanda.nohttp.download.Downloader;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView text, json;
    private Button start;
    private ImageView img;
    private RequestQueue requestQueue;
    private static final int NOHTTP_WHAT_String = 0x001;
    private static final int NOHTTP_WHAT_Json = 0x002;
    private static final int NOHTTP_WHAT_Image = 0x003;
    private static final int NOHTTP_WHAT_Download = 0x004;
    private static final String flag = "test";
    private String url = "http://10.230.140.103:8080/Json/servlet/JsonAction";
    private String imgUrl = "http://10.230.140.103:8080/Upload/upload/star1.png";
    private String fileUrl = "http://10.230.140.103:8080/Upload/upload/star1.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        json = (TextView) findViewById(R.id.json);
        start = (Button) findViewById(R.id.start);
        img = (ImageView) findViewById(R.id.img);
        start.setOnClickListener(this);
        requestQueue = App.initOnHttp();

    }

    @Override
    public void onClick(View v) {
        Request<String> stringRequest = NoHttp.createStringRequest(url, RequestMethod.POST);
        stringRequest.add("action_flag", "person");
//        Request<JSONObject> request=NoHttp.createJsonObjectRequest(url,RequestMethod.GET);
        Request<JSONArray> jsonRequest = NoHttp.createJsonArrayRequest(url, RequestMethod.GET);
        jsonRequest.add("action_flag", "jsonArray");
        Request<Bitmap> bitmapRequest = NoHttp.createImageRequest(imgUrl, RequestMethod.GET);
        requestQueue.add(NOHTTP_WHAT_String, stringRequest, onResponseListener);
        requestQueue.add(NOHTTP_WHAT_Json, jsonRequest, onResponseListener);
        requestQueue.add(NOHTTP_WHAT_Image, bitmapRequest, onResponseListener);


    }

    private OnResponseListener onResponseListener = new OnResponseListener() {
        @Override
        public void onStart(int what) {
            Log.v(flag, "请求开始");
        }

        @Override
        public void onSucceed(int what, Response response) {
            switch (what) {
                case NOHTTP_WHAT_String:
                    text.setText((String) response.get());
                    break;
                case NOHTTP_WHAT_Json:
                    json.setText(((JSONArray) response.get()).toString());
                    break;
                case NOHTTP_WHAT_Image:
                    img.setImageBitmap((Bitmap) response.get());
                    break;
            }
        }

        @Override
        public void onFailed(int what, Response response) {
            Log.v(flag, "请求失败:"+response.responseCode());
        }

        @Override
        public void onFinish(int what) {
            Log.v(flag, "请求结束");
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        requestQueue.cancelAll();
        requestQueue.stop();
    }
}

