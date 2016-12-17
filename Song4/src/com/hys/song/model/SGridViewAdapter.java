package com.hys.song.model;

import com.bumptech.glide.Glide;
import com.hys.song.R;
import com.hys.song.view.MyBitmapImageViewTarget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class SGridViewAdapter extends BaseAdapter {
	private Context context;
	private int[] data;

	public SGridViewAdapter(Context context, int[] data) {
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
					R.layout.address_item_layout, null);
			item.itemImage = (ImageView) convertView
					.findViewById(R.id.address_item);
			convertView.setTag(item);
		} else {
			item = (ItemView) convertView.getTag();
		}

//		Glide.with(context).load(data[position]).asBitmap()
//				.placeholder(R.drawable.cushion)
//				.into(new MyBitmapImageViewTarget(item.itemImage));
		item.itemImage.setImageResource(data[position]);
		return convertView;
	}

	class ItemView {
		private ImageView itemImage;
	}

}
