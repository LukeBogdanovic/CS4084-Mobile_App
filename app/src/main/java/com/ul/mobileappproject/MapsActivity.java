package com.ul.mobileappproject;


import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
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

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {

    EditText locSearch;
    ImageView searchIcon;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    private boolean isPermissionGranted;

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Map");
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        locSearch = findViewById(R.id.search);
        searchIcon = findViewById(R.id.search_icon);

        //geoLocate if searchIcon is pressed
        searchIcon.setOnClickListener(this::geoLocate);
        checkMyPermission();
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }
    /**
     * use Geocoder to get addresses matching a String,
     * get latitude and longitude from the first address
     * use gottoLocation(latitude,longitude) method to change cameras position to the latitude and longitude
     * use latitude and longitude to create a marker on the map
     * @param view
     */
    private void geoLocate(View view) {
        String locationName = locSearch.getText().toString();

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addressList = geocoder.getFromLocationName(locationName, 1);

            if (addressList.size() > 0) {
                Address address = addressList.get(0);
                double latitude = address.getLatitude();
                double longitude = address.getLongitude();

                gotoLocation(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Change maps location to provided latitude and longitude
     * @param latitude latitude for use with google maps
     * @param longitude longitude for use with google maps
     */
    private void gotoLocation(double latitude, double longitude) {
        LatLng LatLng = new LatLng(latitude, longitude);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(LatLng, 16);
        mMap.moveCamera(cameraUpdate);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add markers for one nightclub, 5 pubs and 3 restaurants .
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
        LatLng angelLane = new LatLng(52.6637988, -8.6244057);
        mMap.addMarker(new MarkerOptions().position(angelLane).title("Angel Lane: Nightclub").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));

        //add Pub markers
        // Add a marker onto Stables
        LatLng stables = new LatLng(52.6730436, -8.5709671);
        mMap.addMarker(new MarkerOptions().position(stables).title("Stables: Pub").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        // Add a marker onto Stables
        LatLng hurlers = new LatLng(52.6677589, -8.5601482);
        mMap.addMarker(new MarkerOptions().position(hurlers).title("Hurlers: Pub").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        // Add a marker onto Costello's
        LatLng costellos = new LatLng(52.6602369, -8.628583);
        mMap.addMarker(new MarkerOptions().position(costellos).title("Costello's: Pub").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        // Add a marker onto Molly's
        LatLng mollys = new LatLng(52.6642618, -8.6251827);
        mMap.addMarker(new MarkerOptions().position(mollys).title("Molly's: Pub").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        // Add a marker onto The Red Hen
        LatLng redHen = new LatLng(52.6647275, -8.6299033);
        mMap.addMarker(new MarkerOptions().position(redHen).title("Molly's: Pub").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        //Add 3 restaurant markers
        // Add a marker onto Boojum
        LatLng boojum = new LatLng(52.6645601, -8.627902);
        mMap.addMarker(new MarkerOptions().position(boojum).title("Boojum: Restaurant").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));

        // Add a marker onto SpitJacks
        LatLng spitJack = new LatLng(52.6636116, -8.6303178);
        mMap.addMarker(new MarkerOptions().position(spitJack).title("Spit Jack's: Restaurant").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));

        // Add a marker onto Taikichi
        LatLng taikichi = new LatLng(52.6621507, -8.6332915);
        mMap.addMarker(new MarkerOptions().position(taikichi).title("taikichi: Restaurant").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));

    }

    /**
     * use external 'com.karumi:dexter:6.2.3' to ask for permissions
     * check if user has allowed location services use on the app
     * if allowed set isPermissionGranted true
     * if denied set isPermissionGranted false
     * if not accepted or denied should be asked again
     */
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

    /**
     * Closes the navigation drawer if it is open.
     * Otherwise uses the parent class function.
     */
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Closes the navigation drawer if it is open.
     * Otherwise uses the parent class function.
     *
     * @param menuItem
     * @return boolean
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent homeIntent = new Intent(MapsActivity.this, DashboardActivity.class);
                startActivity(homeIntent);
                break;
            case R.id.nav_timer:
                Intent timerIntent = new Intent(MapsActivity.this, ClockActivity.class);
                startActivity(timerIntent);
                break;
            case R.id.nav_checklist:
                Intent checklistIntent = new Intent(MapsActivity.this, ChecklistActivity.class);
                startActivity(checklistIntent);
                break;
            case R.id.nav_counter:
                Intent counterIntent = new Intent(MapsActivity.this, DrinksCountActivity.class);
                startActivity(counterIntent);
                break;
            case R.id.nav_games:
                Intent gamesIntent = new Intent(MapsActivity.this, GameInstructionsActivity.class);
                startActivity(gamesIntent);
                break;
            case R.id.nav_drinkaware:
                Intent drinkawareIntent = new Intent(MapsActivity.this, DrinkawareActivity.class);
                startActivity(drinkawareIntent);
                break;
            case R.id.nav_logout:
                Intent logoutIntent = new Intent(MapsActivity.this, MainActivity.class);
                logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logoutIntent);
                FirebaseAuth.getInstance().signOut();
                break;
            case R.id.nav_map:
                break;
            case R.id.nav_profile:
                Intent profileIntent = new Intent(MapsActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
                break;
        }
        return true;
    }
}