package com.hys.hugh_baidumap2;

import java.util.ArrayList;
import java.util.List;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.ItemizedOverlay;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.OverlayItem;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends MapActivity {

	private MapView mapView;
	private BMapManager bMapManager;
	private String mapKey="A270F85CD72A01E8519A9677A75FB4016ED9A5A3";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mapView = (MapView) findViewById(R.id.mapview);
		bMapManager=new BMapManager(this);
		bMapManager.init(mapKey, new MKGeneralListener() {

			@Override
			public void onGetPermissionState(int arg0) {
				// TODO Auto-generated method stub
				if (arg0 == 300) {
					Toast.makeText(MainActivity.this, "你所输入的key有误，请核实。",
							Toast.LENGTH_LONG).show();
				}

			}

			@Override
			public void onGetNetworkState(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		this.initMapActivity(bMapManager);
		MapController mapController = mapView.getController();
		mapController.setZoom(12);
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(true);
		Drawable drawable=getResources().getDrawable(R.drawable.iconmarka);
		mapView.getOverlays().add(new MyItemizedOverLay(drawable));
	}

	class MyItemizedOverLay extends ItemizedOverlay<OverlayItem>{
		
		private List<OverlayItem> list;
		private double mLat1=39.90923;
		private double mLon1=116.397428;
		
		private double mLat2=39.91923;
		private double mLon2=116.377428;
		
		private double mLat3=39.80923;
		private double mLon3=116.317428;

		public MyItemizedOverLay(Drawable drawable) {
			super(drawable);
			// TODO Auto-generated constructor stub
			list=new ArrayList<OverlayItem>();
			GeoPoint geoPoint1=new GeoPoint((int)(mLat1*1E6), (int)(mLon1*1E6));
			GeoPoint geoPoint2=new GeoPoint((int)(mLat2*1E6), (int)(mLon2*1E6));
			GeoPoint geoPoint3=new GeoPoint((int)(mLat3*1E6), (int)(mLon3*1E6));
			
			OverlayItem overlayItem1=new OverlayItem(geoPoint1, "point1", "point1");
			OverlayItem overlayItem2=new OverlayItem(geoPoint2, "point2", "point2");
			OverlayItem overlayItem3=new OverlayItem(geoPoint3, "point3", "point3");
			
			list.add(overlayItem1);
			list.add(overlayItem2);
			list.add(overlayItem3);
			populate();
			
		}

		@Override
		protected OverlayItem createItem(int arg0) {
			// TODO Auto-generated method stub
			return list.get(arg0);
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return list.size();
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
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (bMapManager != null) {
			bMapManager.stop();
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
