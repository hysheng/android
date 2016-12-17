package com.hys.handler_databy_internet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.hys.handler_databy_internet.tools.CommonUrl;
import com.hys.handler_databy_internet.tools.DownloadImage;
import com.hys.handler_databy_internet.tools.DownloadImage.ImageCallBack;

import android.R.integer;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ListView listView;
	private MyAdapter adapter;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView=(ListView)findViewById(R.id.prolist);
		adapter = new MyAdapter(this);
		progressDialog = new ProgressDialog(this);
		progressDialog.setTitle("Tip");
		progressDialog.setMessage("Downloading..,Please wait!");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		new MyTask().execute(CommonUrl.pro_url);

	}

	class MyTask extends AsyncTask<String, Void, List<Map<String, Object>>> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressDialog.show();
		}

		@Override
		protected List<Map<String, Object>> doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(params[0]);
			try {
				HttpResponse httpResponse = httpClient.execute(httpPost);
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					String jsonString = EntityUtils.toString(
							httpResponse.getEntity(), "utf-8");
					JSONObject jsonObject = new JSONObject(jsonString);
					JSONArray jsonArray = jsonObject.getJSONArray("prolist");
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject2 = jsonArray.getJSONObject(i);
						Map<String, Object> map = new HashMap<String, Object>();
						Iterator<String> iterator = jsonObject2.keys();
						while (iterator.hasNext()) {
							String key = iterator.next();
							Object value = jsonObject2.get(key);
							map.put(key, value);

						}
						list.add(map);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			return list;
		}

		@Override
		protected void onPostExecute(List<Map<String, Object>> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			adapter.setData(result);
			listView.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			progressDialog.dismiss();
		}

	}

	class MyAdapter extends BaseAdapter {

		private List<Map<String, Object>> list;
		private LayoutInflater inflater;
		private Context context;

		public MyAdapter(Context context) {
			this.context = context;
			inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		public void setData(List<Map<String, Object>> list) {
			this.list = list;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = null;
			if (convertView == null) {
				view = inflater.inflate(R.layout.item, null);
			} else {
				view = convertView;
			}
			TextView proname = (TextView) view.findViewById(R.id.proname);
			TextView proprice = (TextView) view.findViewById(R.id.proprice);
			TextView proaddress = (TextView) view.findViewById(R.id.proaddress);
			final ImageView imageView = (ImageView) view.findViewById(R.id.proimg);
			proname.setText(list.get(position).get("proname").toString());
			proprice.setText(list.get(position).get("proprice").toString());
			proaddress.setText(list.get(position).get("proaddress").toString());
			DownloadImage downloadImage = new DownloadImage(CommonUrl.img_url
					+ list.get(position).get("proimage").toString());
			downloadImage.loadImage(new ImageCallBack() {
				
				@Override
				public void setImageBitmap(Bitmap bitmap) {
					// TODO Auto-generated method stub
					imageView.setImageBitmap(bitmap);
				}
			});
			return view;
		}

	}
}