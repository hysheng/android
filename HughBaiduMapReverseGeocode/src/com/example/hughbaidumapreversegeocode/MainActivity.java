package com.example.hughbaidumapreversegeocode;

import java.util.ArrayList;
import java.util.List;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.ItemizedOverlay;
import com.baidu.mapapi.MKAddrInfo;
import com.baidu.mapapi.MKBusLineResult;
import com.baidu.mapapi.MKDrivingRouteResult;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.MKPoiResult;
import com.baidu.mapapi.MKSearch;
import com.baidu.mapapi.MKSearchListener;
import com.baidu.mapapi.MKSuggestionResult;
import com.baidu.mapapi.MKTransitRouteResult;
import com.baidu.mapapi.MKWalkingRouteResult;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.OverlayItem;
import com.baidu.mapapi.PoiOverlay;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends MapActivity implements OnClickListener {

	private String key = "A270F85CD72A01E8519A9677A75FB4016ED9A5A3";
	private MapView mapView;
	private BMapManager bMapManager;
	private MKSearch mkSearch;
	private Button geocode, reversegeocode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bMapManager = new BMapManager(this);
		bMapManager.init(key, new MKGeneralListener() {

			@Override
			public void onGetPermissionState(int arg0) {
				// TODO Auto-generated method stub
				if (arg0 == 300) {
					Toast.makeText(MainActivity.this, "key is wrong",
							Toast.LENGTH_LONG).show();

				}

			}

			@Override
			public void onGetNetworkState(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		this.initMapActivity(bMapManager);
		mapView = (MapView) findViewById(R.id.mapview);
//		mapView.setSatellite(true);
		mapView.setBuiltInZoomControls(true);
		MapController mapController = mapView.getController();
		GeoPoint geoPoint = new GeoPoint((int) (39.915 * 1E6),
				(int) (116.404 * 1E6));
		mapController.setCenter(geoPoint);
		mapController.setZoom(12);
		mkSearch = new MKSearch();
		mkSearch.init(bMapManager, new MyListener());
		geocode = (Button) findViewById(R.id.geocode);
		reversegeocode = (Button) findViewById(R.id.reversegeocode);
		geocode.setOnClickListener(this);
		reversegeocode.setOnClickListener(this);

	}

	class MyListener implements MKSearchListener {

		@Override
		public void onGetAddrResult(MKAddrInfo res, int error) {
			// TODO Auto-generated method stub
			if (error != 0) {
				String str = String.format("Error Number:%d", error);
				Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG)
						.show();
				return;
			}
			mapView.getController().animateTo(res.geoPt);
			String locInfo = String.format("Longitude:%f Latitude:%f\r\n",
					res.geoPt.getLatitudeE6() / 1e6,
					res.geoPt.getLongitudeE6() / 1e6);
			Toast.makeText(MainActivity.this, locInfo, Toast.LENGTH_LONG)
					.show();
			Drawable maker = getResources().getDrawable(R.drawable.iconmarka);
			maker.setBounds(0, 0, maker.getIntrinsicWidth(),
					maker.getIntrinsicHeight());
			mapView.getOverlays().clear();
			 mapView.getOverlays().add(
			 new OverItem(maker, MainActivity.this, res.geoPt,
			 res.strAddr));
			

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
		public void onGetPoiResult(MKPoiResult res, int type, int error) {
			// TODO Auto-generated method stub
			if (error != 0 || res == null) {
				Toast.makeText(MainActivity.this, "resolving is fail",
						Toast.LENGTH_LONG).show();
				return;
			}
			if (res != null && res.getCurrentNumPois() > 0) {
				GeoPoint geoPoint = res.getAllPoi().get(0).pt;
				mapView.getController().animateTo(geoPoint);
				String strInfo = String.format("Latitude:%d Longitude:%f\r\n",
						geoPoint.getLatitudeE6() / 1e6,
						geoPoint.getLongitudeE6() / 1e6);
				strInfo += "\r\n¸½½üÓÐ£º";
				for (int i = 0; i < res.getAllPoi().size(); i++) {
					strInfo += (res.getAllPoi().get(i).name + ";");
				}
				Toast.makeText(MainActivity.this, strInfo, Toast.LENGTH_LONG)
						.show();

			}

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
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (bMapManager != null) {
			bMapManager.start();
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.geocode) {
			EditText city = (EditText) findViewById(R.id.city);
			EditText addr = (EditText) findViewById(R.id.addr);
			mkSearch.geocode(city.getText().toString(), addr.getText()
					.toString());
		} else {
			EditText lat = (EditText) findViewById(R.id.lat);
			EditText lon = (EditText) findViewById(R.id.lon);
			GeoPoint geoPoint = new GeoPoint(Integer.parseInt(lat.getText()
					.toString()), Integer.parseInt(lon.getText().toString()));
			mkSearch.reverseGeocode(geoPoint);
		}

	}

	class OverItem extends ItemizedOverlay<OverlayItem> {

		private List<OverlayItem> list = new ArrayList<OverlayItem>();

		public OverItem(Drawable maker, Context context, GeoPoint pt,
				String title) {
			super(boundCenterBottom(maker));
			// TODO Auto-generated constructor stub
			list.add(new OverlayItem(pt, title, null));
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

		@Override
		public boolean onSnapToItem(int i, int j, Point point, MapView mapView) {
			// TODO Auto-generated method stub
			Log.e("test", "enter onSnapToItem()!");
			return false;
		}

	}
}
