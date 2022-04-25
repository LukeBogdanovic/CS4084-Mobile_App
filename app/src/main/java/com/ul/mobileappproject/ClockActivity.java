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

    /**
     * Initializes the user interface elements from the elements in the xml file.
     * Initializes the navigation drawer user interface.
     * Loads the stopwatch as the default fragment.
     * Sets listeners for buttons to change between fragments.
     *
     * @param savedInstanceState
     */
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

        // loading the stopwatch initially
        loadFragment(new StopWatchFragment());

        // listeners for timer and stopwatch buttons to change fragment
        timerFragment.setOnClickListener(view -> loadFragment(new TimerFragment()));
        stopwatchFragment.setOnClickListener(view -> loadFragment(new StopWatchFragment()));
    }

    /**
     * Loads the selected fragment onto the activity
     * @param fragment
     */
    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_holder, fragment);
        ft.commit();
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
                Intent homeIntent = new Intent(ClockActivity.this, DashboardActivity.class);
                startActivity(homeIntent);
                break;
            case R.id.nav_profile:
                Intent profileIntent = new Intent(ClockActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
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
                logoutIntent.setFlags(logoutIntent.FLAG_ACTIVITY_CLEAR_TOP);
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
