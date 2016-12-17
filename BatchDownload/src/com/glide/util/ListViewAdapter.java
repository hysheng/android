package com.glide.util;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.DrawableRequestBuilder;


import com.bumptech.glide.Glide;
import com.hys.batchdownload.R;

public class ListViewAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflater;
	private String[] urlData;
	
	
	public ListViewAdapter(Context context,String[] urlData){
		this.context=context;
		this.urlData=urlData;
		inflater=LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return urlData.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return urlData[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder=null;
		if(convertView==null){
			convertView=inflater.inflate(R.layout.item, null);
			viewHolder=new ViewHolder();
			viewHolder.textView=(TextView) convertView.findViewById(R.id.text);
			viewHolder.imageView=(ImageView) convertView.findViewById(R.id.image);
			convertView.setTag(viewHolder);
		}else {
			viewHolder=(ViewHolder) convertView.getTag();
		}
		viewHolder.textView.setText("标题信息测试------"+position);
		viewHolder.imageView.setTag(urlData[position]);
		//glide缓存路径是 /mnt/sdcard/Android/data/packagename/cache
		DrawableRequestBuilder<String> requestBuilder=Glide.with(context).load(urlData[0]);
		requestBuilder.into(viewHolder.imageView);
		
		requestBuilder.error(R.drawable.ic_launcher);
		return convertView;
	}
	private class ViewHolder{
		TextView textView;
		ImageView imageView;
	}
	public void destroy(){
	}
}
