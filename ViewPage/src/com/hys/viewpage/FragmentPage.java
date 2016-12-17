package com.hys.viewpage;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FragmentPage extends ListFragment implements OnScrollListener {
	private String path = "http://172.18.5.42:8080/Json/servlet/JsonAction?pageNum=";
	private static int pageNum = 1;
	private MyAdapter adapter;
	private boolean scrollingFlag = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		adapter=new MyAdapter();
		new MyTask().execute(path+pageNum);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragmentlist, null);
		return view;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		getListView().setOnScrollListener(this);
	}

	class MyTask extends AsyncTask<String, Void, List<String>> {

		@Override
		protected List<String> doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<String> list = new ArrayList<String>();
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(params[0]);
			HttpResponse response = null;
			try {
				response = httpClient.execute(httpPost);
				if (response.getStatusLine().getStatusCode() == 200) {
					String jsonString = EntityUtils.toString(
							response.getEntity(), "utf-8");
					Gson gson=new Gson();
					list=gson.fromJson(jsonString,new TypeToken<List<String>>(){}.getType());
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				httpClient.getConnectionManager().shutdown();
			}
			return list;
		}

		@Override
		protected void onPostExecute(List<String> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			adapter.bindData(result);
			setListAdapter(adapter);
			adapter.notifyDataSetChanged();
			pageNum++;
			Log.v("test", "pageNum========"+pageNum);
		}

	}

	class MyAdapter extends BaseAdapter {
		private List<String> list;

		public void bindData(List<String> list) {
			this.list = list;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
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
			View view;
			if (convertView == null) {
				view = LayoutInflater.from(getActivity()).inflate(
						R.layout.item, null);

			} else {
				view = convertView;
			}
			TextView textView = (TextView) view.findViewById(R.id.textView);
			textView.setText(list.get(position));
			return view;
		}

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		//表示用户不再滑动屏幕
		if(scrollingFlag&&OnScrollListener.SCROLL_STATE_IDLE==scrollState){
			new MyTask().execute(path+pageNum);
		}

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		if (firstVisibleItem + visibleItemCount == totalItemCount
				&& totalItemCount != 0) {
			scrollingFlag=true;
		}else {
			scrollingFlag=false;
		}
		
	}

}
