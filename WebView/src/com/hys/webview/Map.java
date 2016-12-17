package com.hys.webview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class Map extends Activity {


	private WebView webView;
	private Button place;
	private Button drive;
	private Button bus;
	private AlertDialog alertDialog;
	private final int DRIVEROUT = 0;
	private final int BUSROUT = 1;
	private AlertDialog.Builder builder;
	private Context mcontext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mymap);
		place = (Button) findViewById(R.id.place);
		drive = (Button) findViewById(R.id.drive);
		bus = (Button) findViewById(R.id.bus);
		place.setOnClickListener(listener);
		drive.setOnClickListener(listener);
		bus.setOnClickListener(listener);
		initViews();
		mcontext = Map.this;
	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.place:
				showOneDialog();
				break;
			case R.id.drive:
				showTwoDialog(DRIVEROUT);
				break;
			case R.id.bus:
				showTwoDialog(BUSROUT);
				break;
				
			
			default:
				Toast.makeText(mcontext, R.string.defaultId, Toast.LENGTH_SHORT)
						.show();
			}

		}
	};

	private void initViews() {
		webView=(WebView)findViewById(R.id.map);
		WebSettings webSettings=webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(true);
		webView.addJavascriptInterface(this, "MainActivity");
		webView.loadUrl("file:///android_asset/map.html");
	}

	private void showOneDialog() {
		LayoutInflater inflater=(LayoutInflater) mcontext.getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout=inflater.inflate(R.layout.one_dialog,(ViewGroup) findViewById(R.id.layout_root));
		Button search=(Button)layout.findViewById(R.id.placeFind);
		final EditText editText=(EditText)layout.findViewById(R.id.editPlace);
		search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				findPlace(editText.getText().toString());
				closeDialog();
				
			}
		});
		builder=new AlertDialog.Builder(mcontext);
		builder.setView(layout);
		alertDialog=builder.create();
		alertDialog.show();
		
	}
	private void findPlace(String str){
		String url="javascript:findPlace('"+str+"')";
		webView.loadUrl(url);
	}
	private void showTwoDialog(final int type) {
		LayoutInflater inflater=(LayoutInflater) mcontext.getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout=inflater.inflate(R.layout.two_dialog, (ViewGroup) findViewById(R.id.two_layout_root));
		final EditText start=(EditText)layout.findViewById(R.id.editFrom);
		final EditText end=(EditText)layout.findViewById(R.id.editTo);
		Button search=(Button)layout.findViewById(R.id.btnRouteFind);
		search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(type==DRIVEROUT){
					findDriveRoute(start.getText().toString(), end.getText().toString());
					closeDialog();
				}else {
					findBusRoute(start.getText().toString(), end.getText().toString());
					closeDialog();
				}
				
			}
		});
		builder=new AlertDialog.Builder(mcontext);
		builder.setView(layout);
		alertDialog=builder.create();
		alertDialog.show();
		
	}
	private void findDriveRoute(String from, String to)
	{
		
		String url = "javascript:findDriveRoute('"+from +"','"+to+"')";
		webView.loadUrl(url);
	}
	private void findBusRoute(String from,String to){
		String url="javascript:findBusRoute('"+from+"','"+to+"')";
		webView.loadUrl(url);
	}
	private void closeDialog(){
		if(alertDialog!=null){
			alertDialog.dismiss();
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK&&webView.canGoBack()){
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	/**
	 * 显示查询结果对话框
	 * 注：本方法有map.html中的js代码调用
	 * @param result 查询结果，从html中传递进来
	 */
	public void showResult(String result)
	{
		LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.result_dialog, (ViewGroup) findViewById(R.id.result_dialog));
		TextView txtResult = (TextView) layout.findViewById(R.id.txtResult);
		//把result的<b></b>中的内容改变颜色
		result=result.replace("<b>", "<font color=#BCEE68>");
		result=result.replace("</b>", "</font>");
		txtResult.setText(Html.fromHtml(result));
		//设置TextView出现滚动条
		txtResult.setMovementMethod(ScrollingMovementMethod.getInstance());  
		builder = new AlertDialog.Builder(mcontext);
		builder.setView(layout);
		builder.setPositiveButton("关闭",  new DialogInterface.OnClickListener() 
		{          
			public void onClick(DialogInterface dialog, int id) 
			{                
				dialog.dismiss();           
			}       
		});
		alertDialog = builder.create();
		alertDialog.show();
	}

	
}
