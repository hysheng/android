package com.kj.util;



import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.bitmap.BitmapConfig;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;




import com.hys.batchdownload.R;

public class ListViewAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflater;
	private String[] urlData;
	private KJBitmap kjBitmap;
	private BitmapConfig config;
	
	
	public ListViewAdapter(Context context,String[] urlData){
		this.context=context;
		this.urlData=urlData;
		inflater=LayoutInflater.from(context);
		config=new BitmapConfig();
		kjBitmap=new KJBitmap(config);
		
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
		kjBitmap.display(viewHolder.imageView, urlData[position]);
		return convertView;
	}
	private class ViewHolder{
		TextView textView;
		ImageView imageView;
	}
	public void destroy(){
	}
}
