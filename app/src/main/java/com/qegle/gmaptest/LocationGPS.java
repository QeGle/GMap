package com.qegle.gmaptest;


import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;

import java.util.Date;

public class LocationGPS implements ILocation {
	TextView textViewGPS;
	private LocationManager locationManager;
	StringBuilder sbGPS = new StringBuilder();
	String PROVIDER;

	Activity activity;

	public LocationGPS(Activity activity) {
		this.activity = activity;
		textViewGPS = (TextView) this.activity.findViewById(R.id.textView_GPS);
		locationManager = (LocationManager) this.activity.getSystemService(Context.LOCATION_SERVICE);
		if (ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return;
		}
		PROVIDER = LocationManager.GPS_PROVIDER;;
		System.out.println("HEEEEEEEEEEELP");
		locationManager.requestLocationUpdates(PROVIDER,
				1000 * 0, 10, locationListener);
	}

	private LocationListener locationListener = new LocationListener() {

		@Override
		public void onLocationChanged(Location location) {
			showLocation(location);
		}


		@Override
		public void onProviderDisabled(String provider) {
		}


		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {

		}

		@Override
		public void onProviderEnabled(String provider) {
			if (ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
				// TODO: Consider calling
				//    ActivityCompat#requestPermissions
				// here to request the missing permissions, and then overriding
				//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
				//                                          int[] grantResults)
				// to handle the case where the user grants the permission. See the documentation
				// for ActivityCompat#requestPermissions for more details.
				return;
			}
			showLocation(locationManager.getLastKnownLocation(provider));
		}
	};
	private void showLocation(Location location) {
		if (location == null)
			return;
		if (location.getProvider().equals(PROVIDER)) {
			textViewGPS.setText(formatLocation(location));
		}
	}

	private String formatLocation(Location location) {
		if (location == null)
			return "";
		return String.format(
				"Coordinates: lat = %1$.4f, lon = %2$.4f, time = %3$tF %3$tT",
				location.getLatitude(), location.getLongitude(), new Date(
						location.getTime()));
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public double setLat() {
		return 0;
	}

	@Override
	public double setLong() {
		return 0;
	}

	@Override
	public double getLat() {
		return 0;
	}

	@Override
	public double getLong() {
		return 0;
	}
}