package com.hys.recyclerview;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerAdapter extends Adapter<RecyclerAdapter.ViewHolder> implements OnClickListener{
	private List<String> datas;
	private Context context;
	private OnRecyclerViewItemClickListener itemClickListener;

	
	public void addItem(String content){
		datas.add(content);
		notifyItemInserted(datas.size()-1);
	}
	public void removeItem(String content){
		int position=datas.indexOf(content);
		datas.remove(position);
		notifyItemRemoved(position);
	}
	public RecyclerAdapter(Context context,List<String> datas){
		this.context=context;
		this.datas=datas;
	}
	public void setOnItemClickListener(OnRecyclerViewItemClickListener itemClickListenerlistener){
		this.itemClickListener=itemClickListenerlistener;
	}

	@Override
	public int getItemCount() {
		return datas.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		viewHolder.textView.setText(datas.get(position));
		viewHolder.itemView.setTag(""+position);
			}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
		View view=LayoutInflater.from(context).inflate(R.layout.item, viewGroup,false);
		view.setOnClickListener(this);
		ViewHolder viewHolder=new ViewHolder(view);
		return viewHolder;
	}
	public class ViewHolder extends RecyclerView.ViewHolder{
		public TextView textView;
		public ViewHolder(View view) {
			super(view);
			textView=(TextView)view.findViewById(R.id.text);
		}
		
	}
	public static interface OnRecyclerViewItemClickListener {
		void onItemClick(View view,String tag);
	}
	@Override
	public void onClick(View view) {
		itemClickListener.onItemClick(view,(String)view.getTag());
		
	}
	
}
