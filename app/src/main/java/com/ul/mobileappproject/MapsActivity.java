package com.ul.mobileappproject;


import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;

import android.Manifest.permission;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.ul.mobileappproject.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    EditText locSearch;
    ImageView searchIcon;

    private boolean isPermissionGranted;

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        locSearch = findViewById(R.id.search);
        searchIcon = findViewById(R.id.search_icon);

        searchIcon.setOnClickListener(this::geoLocate);
        checkMyPermission();
        if (isPermissionGranted) {
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment =
                    (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

        }
    }

    private void geoLocate(View view) {
        String locationName = locSearch.getText().toString();

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addressList = geocoder.getFromLocationName(locationName, 1);

            if (addressList.size() > 0) {
                Address address = addressList.get(0);

                gotoLocation(address.getLatitude(), address.getLongitude());

                mMap.addMarker(new MarkerOptions().position(new LatLng(address.getLatitude(), address.getLongitude())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void gotoLocation(double latitude, double longitude) {
        LatLng LatLng = new LatLng(latitude, longitude);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(LatLng, 18);
        mMap.moveCamera(cameraUpdate);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Stables.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (isPermissionGranted) {
            mMap.setMyLocationEnabled(true);
        }
        //Add Nightclub Marker
        // Add a marker onto Angel Lane
        LatLng angelLane = new LatLng(52.6637988,-8.6244057);
        mMap.addMarker(new MarkerOptions().position(angelLane).title("Angel Lane: Nightclub").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));

        //add Pub markers
        // Add a marker onto Stables
        LatLng stables = new LatLng(52.6730436, -8.5709671);
        mMap.addMarker(new MarkerOptions().position(stables).title("Stables: pub").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        // Add a marker onto Stables
        LatLng hurlers = new LatLng(52.6677589,-8.5601482);
        mMap.addMarker(new MarkerOptions().position(hurlers).title("Hurlers: pub").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        // Add a marker onto Costello's
        LatLng costellos = new LatLng(52.6602369,-8.628583);
        mMap.addMarker(new MarkerOptions().position(costellos).title("Costello's: Pub").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        // Add a marker onto Molly's
        LatLng mollys = new LatLng(52.6642618,-8.6251827);
        mMap.addMarker(new MarkerOptions().position(mollys).title("Molly's: Pub").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        // Add a marker onto The Red Hen
        LatLng redHen = new LatLng(52.6647275,-8.6299033);
        mMap.addMarker(new MarkerOptions().position(redHen).title("Molly's: Pub").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        //Add 3 restaurant markers
        // Add a marker onto Boojum
        LatLng boojum = new LatLng(52.6645601,-8.627902);
        mMap.addMarker(new MarkerOptions().position(boojum).title("Boojum: Restaurant").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));

        // Add a marker onto SpitJacks
        LatLng spitJack = new LatLng(52.6636116,-8.6303178);
        mMap.addMarker(new MarkerOptions().position(spitJack).title("Spit Jack's: Restaurant").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));

        // Add a marker onto Taikichi
        LatLng taikichi = new LatLng(52.6621507,-8.6332915);
        mMap.addMarker(new MarkerOptions().position(taikichi).title("taikichi: Restaurant").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));

    }

    private void checkMyPermission() {
        Dexter.withContext(this).withPermission(permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                Toast.makeText(MapsActivity.this, "permission Granted", Toast.LENGTH_SHORT).show();
                isPermissionGranted = true;
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), "");
                intent.setData(uri);
                startActivity(intent);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }
}