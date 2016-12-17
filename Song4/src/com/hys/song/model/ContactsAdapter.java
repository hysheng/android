package com.hys.song.model;

import com.bumptech.glide.Glide;
import com.hys.song.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactsAdapter extends BaseAdapter {
	private Context context;
	private int[] headIds;
	private String[] nameData;
	private String[] dayData;

	public ContactsAdapter(Context context, int[] headIds, String[] nameData,
			String[] dayData) {
		this.context = context;
		this.headIds = headIds;
		this.nameData = nameData;
		this.dayData = dayData;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return headIds.length;
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
		ItemView item;
		if (convertView == null) {
			item = new ItemView();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.contact_item, null);
			item.image=(ImageView)convertView.findViewById(R.id.contact_head);
			item.name=(TextView)convertView.findViewById(R.id.contact_name);
			item.day=(TextView)convertView.findViewById(R.id.birthday_num);
			convertView.setTag(item);
		}else {
			item=(ItemView)convertView.getTag();
		}
		item.name.setText(nameData[position]);
		item.day.setText(dayData[position]);
		Glide.with(context).load(headIds[position]).into(item.image);
		return convertView;
	}

	class ItemView {
		public ImageView image;
		public TextView name;
		public TextView day;
	}

}
