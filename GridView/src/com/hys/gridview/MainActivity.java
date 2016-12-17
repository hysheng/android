package com.hys.gridview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private GridView gridView;
	private String[] titles={"Girl1","Girl2","Girl3","Girl4","Girl5","Girl6","Girl7","Girl8","Girl9"};
	private int[] images={R.drawable.o,R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p5
			,R.drawable.p6,R.drawable.p7,R.drawable.p8};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		gridView=(GridView)findViewById(R.id.gridview);
		PictureAdapter adapter=new PictureAdapter(this, titles, images);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.i("test","Postion:"+position);
				Intent intent=new Intent(MainActivity.this,ImageActivity.class);
				intent.putExtra("id", images[position]);
				startActivity(intent);
				
				
			}
		});

	}

}

class PictureAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<Picture> pictures;

	public PictureAdapter(Context context, String[] titles, int[] images) {
		super();
		inflater = LayoutInflater.from(context);
		pictures = new ArrayList<Picture>();
		
		for (int i = 0; i < titles.length; i++) {
			Picture picture = new Picture();
			picture.setTitle(titles[i]);
			picture.setImageId(images[i]);
			pictures.add(picture);
		}
	}

	@Override
	public int getCount() {
		if (pictures != null) {
			return pictures.size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item, null);
			viewHolder = new ViewHolder();
			viewHolder.textView = (TextView) convertView
					.findViewById(R.id.complain);
			viewHolder.imageView = (ImageView) convertView
					.findViewById(R.id.image);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		String infoString=pictures.get(position).getTitle();
		viewHolder.textView.setText(infoString);
		viewHolder.imageView.setImageResource(pictures.get(position)
				.getImageId());
		Log.i("test", ">>>>>" + position);
		return convertView;
	}

}

class ViewHolder {
	public TextView textView;
	public ImageView imageView;
}

class Picture {
	private String title;
	private int imageId;

	public Picture() {
	}

	public Picture(String title, int imageId) {
		this.title = title;
		this.imageId = imageId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

}
