package com.hys.hugh_baidumap_search_driving;

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
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.RouteOverlay;
import com.hys.hugh_baidumap2.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends MapActivity {

	private MapView mapView;
	private BMapManager bMapManager;
	private String mapKey="A270F85CD72A01E8519A9677A75FB4016ED9A5A3";
	private MKSearch mkSearch;
	private MKPlanNode start;
	private MKPlanNode end;

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
		mkSearch=new MKSearch();
		mkSearch.init(bMapManager, new MySearch());
		start=new MKPlanNode();
		start.pt=new GeoPoint((int)(39.915*1E6), (int)(116.404*1E6));
		end=new MKPlanNode();
		end.pt=new GeoPoint(40057031, 116307852);
		mkSearch.setDrivingPolicy(MKSearch.ECAR_TIME_FIRST);
		mkSearch.drivingSearch(null, start, null, end);
		
		
	}

	public class MySearch implements MKSearchListener{

		@Override
		public void onGetAddrResult(MKAddrInfo arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onGetDrivingRouteResult(MKDrivingRouteResult result, int arg1) {
			// TODO Auto-generated method stub
			if(result==null){
				return;
			}
			RouteOverlay routeOverlay=new RouteOverlay(MainActivity.this, mapView);
			routeOverlay.setData(result.getPlan(0).getRoute(0));
			mapView.getOverlays().add(routeOverlay);
			mapView.invalidate();
			
		}

		@Override
		public void onGetPoiResult(MKPoiResult result, int type, int iError) {
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
		public void onGetWalkingRouteResult(MKWalkingRouteResult arg0, int arg1) {
			// TODO Auto-generated method stub
			
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
