package com.hys.hughmapbuslineoverlay;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKAddrInfo;
import com.baidu.mapapi.MKBusLineResult;
import com.baidu.mapapi.MKDrivingRouteResult;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.MKPoiInfo;
import com.baidu.mapapi.MKPoiResult;
import com.baidu.mapapi.MKSearch;
import com.baidu.mapapi.MKSearchListener;
import com.baidu.mapapi.MKSuggestionResult;
import com.baidu.mapapi.MKTransitRouteResult;
import com.baidu.mapapi.MKWalkingRouteResult;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.RouteOverlay;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends MapActivity {

	private MapView mapView;
	private BMapManager bMapManager;
	private String key = "A270F85CD72A01E8519A9677A75FB4016ED9A5A3";
	private MKSearch mkSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mapView = (MapView) findViewById(R.id.mapview);
		bMapManager = new BMapManager(this);
		bMapManager.init(key, new MKGeneralListener() {

			@Override
			public void onGetPermissionState(int arg0) {
				// TODO Auto-generated method stub
				if (arg0 == 300) {
					Toast.makeText(MainActivity.this, "Key is bad!",
							Toast.LENGTH_LONG).show();
				}

			}

			@Override
			public void onGetNetworkState(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		this.initMapActivity(bMapManager);
		mapView.setSatellite(true);
		mkSearch = new MKSearch();
		mkSearch.init(bMapManager, new MyListener());
		mkSearch.poiSearchInCity("北京", "300");

	}

	class MyListener implements MKSearchListener {

		@Override
		public void onGetAddrResult(MKAddrInfo arg0, int arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
			// TODO Auto-generated method stub
			if (arg0 == null || arg1 != 0) {
				Toast.makeText(MainActivity.this, "not found",
						Toast.LENGTH_LONG).show();
				return;
			}
			RouteOverlay routeOverlay = new RouteOverlay(MainActivity.this,
					mapView);
			routeOverlay.setData(arg0.getBusRoute());
			mapView.getOverlays().clear();
			mapView.getOverlays().add(routeOverlay);
			mapView.invalidate();
			mapView.getController().animateTo(arg0.getBusRoute().getStart());

		}

		@Override
		public void onGetDrivingRouteResult(MKDrivingRouteResult arg0, int arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onGetPoiResult(MKPoiResult arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			if (arg0 == null || arg2 != 0) {
				Toast.makeText(MainActivity.this, "not found",
						Toast.LENGTH_LONG).show();
				return;
			}
			MKPoiInfo mkPoiInfo = null;
			int totalPoiNum = arg0.getNumPois();
			for (int i = 0; i < totalPoiNum; i++) {
				mkPoiInfo = arg0.getPoi(i);
				if (mkPoiInfo.ePoiType == 2) {
					break;
				}
			}
			mkSearch.busLineSearch("北京", mkPoiInfo.uid);

		}

		@Override
		public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onGetTransitRouteResult(MKTransitRouteResult arg0, int arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onGetWalkingRouteResult(MKWalkingRouteResult arg0, int arg1) {
			// TODO Auto-generated method stub

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
