package com.hugh.mposition;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private Location location;
    private TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show = (TextView) findViewById(R.id.show);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        updateView(location);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 8, new LocationListener() {


            @Override
            public void onLocationChanged(Location location) {
                updateView(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                updateView(location);
            }

            @Override
            public void onProviderEnabled(String provider) {
                updateView(location);
            }

            @Override
            public void onProviderDisabled(String provider) {
                updateView(location);
            }
        });


    }
    private void updateView(Location newLocation){
        if(newLocation!=null){
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append("实时的地理位置信息:\n");
            stringBuilder.append("经度:");
            stringBuilder.append(newLocation.getLongitude());
            stringBuilder.append("\n纬度:");
            stringBuilder.append(newLocation.getLatitude());
            stringBuilder.append("\n高度:");
            stringBuilder.append(newLocation.getAltitude());
            stringBuilder.append("\n速度:");
            stringBuilder.append(newLocation.getSpeed());
            stringBuilder.append("\n方向:");
            stringBuilder.append(newLocation.getBearing());
            show.setText(stringBuilder.toString());
        }
    }
}
