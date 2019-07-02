package com.example.guzo.Museums;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.guzo.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MuseumMap extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        LocationListener,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMapLongClickListener, GoogleApiClient.OnConnectionFailedListener {
private GoogleMap mMap;
public static  final int RequestPermissionCode = 1;
GoogleApiClient mGoogleApiClient;

private double longitude;
private double latitude;

private  GoogleApiClient googleApiClient;
private  String TAG="gps";
public static final int REQUEST_CHECK_SETTINGS = 123;
LocationRequest mLocationRequest;
int INTERVAL = 1000;
int FASTEST_INTERVAL = 500;
FloatingActionButton floatingActionButton;
Polyline polyline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_map);
        SupportMapFragment mapFragment =(SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapMuseum);
        mapFragment.getMapAsync(this);
floatingActionButton =(FloatingActionButton)findViewById(R.id.current_fab);
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mLocationRequest =new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
floatingActionButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        getCurrentLocation();
    }
});
    }

    private ResultCallback<LocationSettingsResult> mResultCallbackFromSetting = new ResultCallback<LocationSettingsResult>() {
        @Override
        public void onResult(@NonNull LocationSettingsResult result) {
            final Status status = result.getStatus();

            switch (status.getStatusCode()) {
                case LocationSettingsStatusCodes
                        .SUCCESS:
                    break;

                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                    try {
                        status.startResolutionForResult(
                                MuseumMap.this,
                                REQUEST_CHECK_SETTINGS
                        );
                    } catch (IntentSender.SendIntentException e) {

                    }
                    break;
                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                    Log.e(TAG, "setting change unavalable");
                    break;

            }


        }
    };
    @Override
    public void onMapReady(GoogleMap googleMap) {
         mMap = googleMap;

         LatLng sydney = new LatLng(-34,151);
         mMap.addMarker(new MarkerOptions().position(sydney).title("marker"));
         mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

         mMap.setOnMarkerDragListener(this);
         mMap.setOnMapLongClickListener(this);
         if (checkPermission()){
             buildGoogleApiClient();

             LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                     .addLocationRequest(mLocationRequest);
             PendingResult<LocationSettingsResult> result =
                     LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient,builder.build());
             result.setResultCallback(mResultCallbackFromSetting);

         }else {
             requestPermission();
         }
    }


    private void requestPermission() {
        ActivityCompat.requestPermissions(MuseumMap.this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        },RequestPermissionCode);
    }
    @Override
    public  void onRequestPermissionsResult(int requestCode, String permissions[],int[] grantResults){
        switch (requestCode){
            case  RequestPermissionCode:
                if (grantResults.length > 0){
                    boolean finelocation = grantResults[0]== PackageManager.PERMISSION_GRANTED;
                    boolean coarselocation = grantResults[1]== PackageManager.PERMISSION_GRANTED;
                    if (finelocation && coarselocation){
                        if (checkPermission())
                            buildGoogleApiClient();
                        Toast.makeText(MuseumMap.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MuseumMap.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
    private boolean checkPermission() {
        int FirstPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_COARSE_LOCATION);

        return  FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED;

    }
 protected  synchronized  void  buildGoogleApiClient(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
 }
    @Override
    public void onLocationChanged(Location location) {

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

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        getCurrentLocation();

    }

    @Override
    public void onConnectionSuspended(int i) {

    }


    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
       googleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
       mMap.clear();

       mMap.addMarker(new MarkerOptions()
                  .position(latLng)
       .draggable(true));
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
          latitude =marker.getPosition().latitude;
          longitude =marker.getPosition().longitude;
          moveMap();
    }

    private void getCurrentLocation(){
        Location location = null;
        if (checkPermission()){
            location=LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        }
        if (location != null){
            longitude = location.getLongitude();
            latitude = location.getLatitude();
            moveMap();
        }
    }

    private void moveMap() {
        String msg= latitude +", "+longitude;
        LatLng latLng = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions()
         .position(latLng)
        .draggable(true)
        .title("Current Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
