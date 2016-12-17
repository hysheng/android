package com.hys.batchdownload;

import com.kj.util.ListViewAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
/*
 * 采用二级缓存机制，图片先尝试从内存(一级缓存)中读取，如果没有再从硬盘或者网络(二级缓存)中读取
 */

public class MainActivity extends Activity {
	private ListView listView;
	private ListViewAdapter adapter;
	private String[] urlData={
			"http://img31.mtime.cn/pi/2013/10/21/153124.63804810_1000X1000.jpg",
			"http://img31.mtime.cn/pi/2013/10/21/153122.70681717_1000X1000.jpg",
			"http://img31.mtime.cn/pi/2013/10/21/153121.86152520_1000X1000.jpg",
			"http://img31.mtime.cn/pi/2013/10/21/153112.17758012_1000X1000.jpg",
			"http://img31.mtime.cn/pi/2013/10/21/153103.74128091_1000X1000.jpg",
			"http://img31.mtime.cn/pi/2013/10/21/153102.87249594_1000X1000.jpg",
			"http://img31.mtime.cn/pi/2015/09/15/104414.30448480_1000X1000.jpg",
			"http://www.weimeixi.com/uploads/allimg/140909/1PI11K4-0.jpg",
			"http://www.weimeixi.com/uploads/allimg/140909/1PI13933-1.jpg",
			"http://www.weimeixi.com/uploads/allimg/140909/1PP11464-4.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listview);
        adapter=new ListViewAdapter(MainActivity.this, urlData);
        listView.setAdapter(adapter);
        
    }
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	listView.setAdapter(null);
    	super.onDestroy();
    	adapter.destroy();
    }
}
