package com.qegle.gmaptest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

	private GoogleMap gMap;

	//ekaterinburg
	private static final double TARGET_LATITUDE = 56.838926;
	private static final double TARGET_LONGITUDE = 60.605702;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
		setContentView(R.layout.activity_maps);
		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		 mapFragment.getMapAsync(this);

	}


	/**
	 * Manipulates the map once available.
	 * This callback is triggered when the map is ready to be used.
	 * This is where we can add markers or lines, add listeners or move the camera. In this case,
	 * we just add a marker near Sydney, Australia.
	 * If Google Play services is not installed on the device, the user will be prompted to install
	 * it inside the SupportMapFragment. This method will only be triggered once the user has
	 * installed Google Play services and returned to the app.
	 */


	@Override
	public void onMapReady(GoogleMap googleMap) {
		gMap = googleMap;
		if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
				== PackageManager.PERMISSION_GRANTED) {
			System.out.println("setMyLocationEnabled");
			gMap.setMyLocationEnabled(true);

		} else {
			// Show rationale and request permission.
		}

		// Add a marker in Sydney and move the camera
		LatLng markerCoord = new LatLng(-34, 151);
		gMap.addMarker(new MarkerOptions().position(markerCoord).title("Marker in Sydney"));
		gMap.moveCamera(CameraUpdateFactory.newLatLng(markerCoord));

	}


	//добавляем маркер на карту
	private void addMarker(double lat, double lng){

		//устанавливаем позицию и масштаб отображения карты
		CameraPosition cameraPosition = new CameraPosition.Builder()
				.target(new LatLng(lat, lng))
				.zoom(15)
				.build();
		CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);


		if(null != gMap){
			gMap.animateCamera(cameraUpdate);
			gMap.addMarker(new MarkerOptions()
					.position(new LatLng(lat, lng))
					.draggable(false)
			);
		}
	}


	public void onClickAPI(View view) {
		Toast.makeText(this,"API",Toast.LENGTH_SHORT).show();
	}

	public void onClickGPS(View view) {

		Intent intent = new Intent(this,GPS_Network_Activity.class);
		startActivity(intent);

		Toast.makeText(this,"GPS",Toast.LENGTH_SHORT).show();

	}

	public void onClickRandom(View view) {
		Toast.makeText(this,"Random",Toast.LENGTH_SHORT).show();

	}

	public void onClickEKB(View view) {
		addMarker(TARGET_LATITUDE,TARGET_LONGITUDE);
	}
}
