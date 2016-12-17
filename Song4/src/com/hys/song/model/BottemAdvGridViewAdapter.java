package com.hys.song.model;

import com.bumptech.glide.Glide;
import com.hys.song.R;
import com.hys.song.view.MyBitmapImageViewTarget;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class BottemAdvGridViewAdapter extends BaseAdapter {
	private Context context;
	private int[] data;

	public BottemAdvGridViewAdapter(Context context, int[] data) {
		this.context = context;
		this.data = data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ItemView item;
		if (convertView == null) {
			item = new ItemView();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.bottom_adv_item, null);
			item.itemImage = (ImageView) convertView
					.findViewById(R.id.bottom_adv_item);
			convertView.setTag(item);
		} else {
			item = (ItemView) convertView.getTag();
		}
//		Glide.with(context).load(data[position]).asBitmap()
//		.placeholder(R.drawable.ic_launcher)
//		.into(new MyBitmapImageViewTarget(item.itemImage));
		//控件的长宽是wrap_content等未知时可以用上面方法来加载，或者给控件的长宽设定值
		Glide.with(context).load(data[position])
		.fitCenter()
		.into(item.itemImage);
		return convertView;
	}

	class ItemView {
		private ImageView itemImage;
	}

}
