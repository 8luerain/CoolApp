package com.example.administrator.coolapp.activity;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.coolapp.R;
import com.example.administrator.coolapp.utils.HttpUtils;

import java.util.List;

/**
 * Created by Administrator on 2015/4/16.
 */
public class MainAty extends Activity {
    TextView tv_location;
    LocationManager locationManager;
    Location mlocation ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_main);
        tv_location = (TextView) findViewById(R.id.tv_main_location);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        getLocation();
        HttpUtils.getHttpResponseAsGET("http://gc.ditu.aliyun.com/regeocoding?l=" + mlocation.getLongitude() + "," + mlocation.getAltitude() + "&type=001");
    }

    protected Location getLocation() {
        final  Location oLocation = null;
        Location lastKnownLocation = null;
        if (locationManager != null) {
            List<String> allProviders = locationManager.getAllProviders();
            if (allProviders.contains(LocationManager.GPS_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,10,locationListener);
            } else if (allProviders.contains(LocationManager.NETWORK_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,5000,10,locationListener);
            }
        }
        return oLocation;
    }

    @Override
    public boolean isDestroyed() {
        return super.isDestroyed();

    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            mlocation = location;

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
}
