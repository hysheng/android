package com.hys.hughbaidumapsearchwalkroute;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MKAddrInfo;
import com.baidu.mapapi.MKBusLineResult;
import com.baidu.mapapi.MKDrivingRouteResult;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.MKPlanNode;
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
	private MKPlanNode start, end;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// mapView.setSatellite(true);
		bMapManager = new BMapManager(this);
		bMapManager.init(key, new MKGeneralListener() {

			@Override
			public void onGetPermissionState(int iError) {
				// TODO Auto-generated method stub
				if (iError == 300) {
					Toast.makeText(MainActivity.this, "ƒ„ ‰»Îµƒkey”–ŒÛ",
							Toast.LENGTH_LONG).show();

				}

			}

			@Override
			public void onGetNetworkState(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		initMapActivity(bMapManager);
		mapView = (MapView) findViewById(R.id.mapview);
		mkSearch = new MKSearch();
		mkSearch.init(bMapManager, new MySearchListener());
		start = new MKPlanNode();
		start.pt = new GeoPoint((int) (39.915 * 1E6), (int) (116.404 * 1E6));
		end = new MKPlanNode();
		end.pt = new GeoPoint(40057031, 116307852);
		mkSearch.walkingSearch(null, start, null, end);

	}

	class MySearchListener implements MKSearchListener {

		@Override
		public void onGetAddrResult(MKAddrInfo arg0, int arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onGetDrivingRouteResult(MKDrivingRouteResult arg0, int arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onGetPoiResult(MKPoiResult arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub

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
		public void onGetWalkingRouteResult(MKWalkingRouteResult result,
				int arg1) {
			// TODO Auto-generated method stub
			if (result == null) {
				return;
			}
			RouteOverlay routeOverlay = new RouteOverlay(MainActivity.this,
					mapView);
			routeOverlay.setData(result.getPlan(0).getRoute(0));
			mapView.getOverlays().add(routeOverlay);
			mapView.invalidate();

		}

	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(bMapManager!=null){
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
