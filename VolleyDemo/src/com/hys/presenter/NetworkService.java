package com.hys.presenter;

import org.json.JSONArray;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.hys.volleydemo.MyApplication;

public class NetworkService {
	public static void sendStringRequest(String url) {
		StringRequest request = new StringRequest(Request.Method.GET, url,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						Log.v("test", response);

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						Log.v("test", error.getMessage());
					}
				});
		MyApplication.getVolleyRequestQueue().add(request);
	}

	public static void sendJsonObjectRequest(String url) {
		JsonObjectRequest request = new JsonObjectRequest(url, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.v("test", response.toString());
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						Log.v("test", error.getMessage());
					}
				});
		MyApplication.getVolleyRequestQueue().add(request);
	}

	public static void sendJsonArrayRequest(String url) {
		JsonArrayRequest request = new JsonArrayRequest(url,
				new Listener<JSONArray>() {

					@Override
					public void onResponse(JSONArray array) {
						// TODO Auto-generated method stub
						Log.v("test", array.toString());
					}

				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						Log.v("test", error.getMessage());
					}
				});
		MyApplication.getVolleyRequestQueue().add(request);
	}

	public static void sendImageRequest(final ImageView view) {
		ImageRequest request = new ImageRequest(
				"http://172.18.5.42:8080/Upload/upload/star1.png",
				new Response.Listener<Bitmap>() {

					@Override
					public void onResponse(Bitmap bitmap) {
						// TODO Auto-generated method stub
						view.setImageBitmap(bitmap);
					}

				}, 0, 0, Config.RGB_565, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						Log.v("test", error.getMessage());
					}
				});
		MyApplication.getVolleyRequestQueue().add(request);
	}
}
