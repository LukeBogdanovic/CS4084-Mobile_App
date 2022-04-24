package com.ul.mobileappproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class ClockActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button timerFragment, stopwatchFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Timers");
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        timerFragment = (Button) findViewById(R.id.timerFragment);
        stopwatchFragment = (Button) findViewById(R.id.stopwatchFragment);

        loadDefault(new StopWatchFragment());

        timerFragment.setOnClickListener(view -> loadFragment(new TimerFragment()));
        stopwatchFragment.setOnClickListener(view -> loadFragment(new StopWatchFragment()));
    }

    private void loadDefault(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_holder, fragment);
        ft.commit();
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_holder, fragment);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent homeIntent = new Intent(ClockActivity.this, DashboardActivity.class);
                startActivity(homeIntent);
                break;
            case R.id.nav_timer:
                break;
            case R.id.nav_checklist:
                Intent checklistIntent = new Intent(ClockActivity.this, ChecklistActivity.class);
                startActivity(checklistIntent);
                break;
            case R.id.nav_counter:
                Intent counterIntent = new Intent(ClockActivity.this, DrinksCountActivity.class);
                startActivity(counterIntent);
                break;
            case R.id.nav_games:
                Intent gamesIntent = new Intent(ClockActivity.this, GameInstructionsActivity.class);
                startActivity(gamesIntent);
                break;
            case R.id.nav_drinkaware:
                Intent drinkawareIntent = new Intent(ClockActivity.this, DrinkawareActivity.class);
                startActivity(drinkawareIntent);
                break;
            case R.id.nav_logout:
                Intent logoutIntent = new Intent(ClockActivity.this, MainActivity.class);
                logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logoutIntent);
                FirebaseAuth.getInstance().signOut();
                break;
            case R.id.nav_map:
                Intent mapsIntent = new Intent(ClockActivity.this, MapsActivity.class);
                startActivity(mapsIntent);
                break;
        }
        return true;
    }
}
