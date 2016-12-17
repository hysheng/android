package com.hys.hughsmechanism;



import com.hys.batchdownload.R;
import com.hys.batchdownload.R.drawable;
import com.hys.batchdownload.R.id;
import com.hys.batchdownload.R.layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflater;
	private String[] urlData;
	private AsyncImageLoader imageLoader;
	private MemoryCache memoryCache;
	
	public ListViewAdapter(Context context,String[] urlData){
		this.context=context;
		this.urlData=urlData;
		inflater=LayoutInflater.from(context);
		memoryCache=new MemoryCache();
		imageLoader=new AsyncImageLoader(context, memoryCache);
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
		Bitmap bitmap=imageLoader.loadBitmap(viewHolder.imageView	, urlData[position]);
		if(bitmap==null){
			viewHolder.imageView.setImageResource(R.drawable.ic_launcher);
		}else {
			viewHolder.imageView.setImageBitmap(bitmap);
		}
		return convertView;
	}
	private class ViewHolder{
		TextView textView;
		ImageView imageView;
	}
	public void destroy(){
		imageLoader.destroy();
	}
}
