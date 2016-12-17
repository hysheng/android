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

public class GoodsSearchAdapter extends BaseAdapter {
	private Context context;
	private int[] goodsImages;
	private String[] goodsNames;
	private String[] goodsPrices;
	private String[] goodsVolume;
	
	
	public GoodsSearchAdapter(Context context, int[] goodsImages,
			String[] goodsNames, String[] goodsPrices, String[] goodsVolume) {
		super();
		this.context = context;
		this.goodsImages = goodsImages;
		this.goodsNames = goodsNames;
		this.goodsPrices = goodsPrices;
		this.goodsVolume = goodsVolume;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return goodsNames.length;
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
					R.layout.goods_item, null);
			item.image=(ImageView)convertView.findViewById(R.id.gods_img);
			item.name=(TextView)convertView.findViewById(R.id.gods_name);
			item.price=(TextView)convertView.findViewById(R.id.gods_price);
			item.volume=(TextView)convertView.findViewById(R.id.sales_volume);
			convertView.setTag(item);
		}else {
			item=(ItemView)convertView.getTag();
		}
		item.name.setText(goodsNames[position]);
		item.price.setText(goodsPrices[position]);
		item.volume.setText(goodsVolume[position]);
		Glide.with(context).load(goodsImages[position]).into(item.image);
		return convertView;
	}
	class ItemView{
		public ImageView image;
		public TextView name;
		public TextView price;
		public TextView volume;
	}

}
