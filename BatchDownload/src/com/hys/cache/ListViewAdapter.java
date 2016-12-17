package com.hys.cache;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.hys.batchdownload.R;


public class ListViewAdapter extends BaseAdapter {
    private Activity mActivity;
    private String[] data;
    private static LayoutInflater inflater=null;
    private AsyncImageLoader imageLoader;//�첽���
    
    public ListViewAdapter(Activity mActivity, String[] d) {
        this.mActivity=mActivity;
        data=d;
        inflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        MemoryCache mcache=new MemoryCache();//�ڴ滺��
        File sdCard = android.os.Environment.getExternalStorageDirectory();//���SD��
		File cacheDir = new File(sdCard, "jereh_cache" );//�����Ŀ¼
        FileCache fcache=new FileCache(mActivity, cacheDir, "news_img");//�ļ�����
        imageLoader = new AsyncImageLoader(mActivity, mcache,fcache);
    }

    public int getCount() {
        return data.length;
    }
    public Object getItem(int position) {
        return position;
    }
    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh=null;
        if(convertView==null){
        	convertView = inflater.inflate(R.layout.item, null);
            vh=new ViewHolder();
            vh.tvTitle=(TextView)convertView.findViewById(R.id.text);
            vh.ivImg=(ImageView)convertView.findViewById(R.id.image);
            convertView.setTag(vh);		
        }else{
        	vh=(ViewHolder)convertView.getTag();
        }
        vh.tvTitle.setText("������Ϣ���ԡ������� "+position);
        vh.ivImg.setTag(data[position]);
        //�첽����ͼƬ���ȴ�һ�����桢�ٶ������桢��������ȡͼƬ
        Bitmap bmp = imageLoader.loadBitmap(vh.ivImg, data[position]);
        if(bmp == null) {
        	vh.ivImg.setImageResource(R.drawable.ic_launcher);
        } else {
        	vh.ivImg.setImageBitmap(bmp);
        }
        return convertView;
    }
    private class ViewHolder{
    	TextView tvTitle;
    	ImageView ivImg;
    }
    public void destroy() {
    	imageLoader.destroy();
    }
}