package com.example.hugh_baidumap;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.Overlay;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends MapActivity {

	private MapView mapView;
	private BMapManager bMapManager;
	private String keyString = "A270F85CD72A01E8519A9677A75FB4016ED9A5A3";
	private MapController mapController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mapView = (MapView) findViewById(R.id.mapView);
		bMapManager = new BMapManager(this);
		bMapManager.init(keyString, new MKGeneralListener() {

			@Override
			public void onGetPermissionState(int arg0) {
				// TODO Auto-generated method stub
				if (arg0 == 300) {
					Toast.makeText(MainActivity.this, "输入的key有误",
							Toast.LENGTH_LONG).show();
				}

			}

			@Override
			public void onGetNetworkState(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		initMapActivity(bMapManager);
		mapView.setBuiltInZoomControls(true);
//		设置交通地图
//		mapView.setTraffic(true);
//		设置卫星地图
		mapView.setSatellite(true);
		mapController = mapView.getController();
//		GeoPoint geoPoint = new GeoPoint((int) (39.915 * 1E6),
//				(int) (116.404 * 1E6));
//		mapController.setCenter(geoPoint);
		
		mapController.setZoom(12);
		mapView.getOverlays().add(new MyOverLay());
		
	}
	
	class MyOverLay extends Overlay{
		
		private GeoPoint geoPoint=new GeoPoint((int) (39.915 * 1E6),
				(int) (116.404 * 1E6));
		
		private Paint paint=new Paint();
		
		@Override
		public void draw(Canvas arg0, MapView arg1, boolean arg2) {
			// TODO Auto-generated method stub
			super.draw(arg0, arg1, arg2);
			Point point=mapView.getProjection().toPixels(geoPoint, null);
			arg0.drawText("@China天安门@", point.x, point.y, paint);
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (bMapManager != null) {
			bMapManager.destroy();
			bMapManager = null;
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (bMapManager != null) {
			bMapManager.start();
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (bMapManager != null) {
			bMapManager.stop();
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
