package com.ul.mobileappproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private Button checkListBtn, clockBtn, countBtn, drinkawarenessBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        initializeUI();
        checkListBtn.setOnClickListener(view -> {
            Intent intent = new Intent(DashboardActivity.this, ChecklistActivity.class);
            startActivity(intent);
        });
        clockBtn.setOnClickListener(view -> {
            Intent intent = new Intent(DashboardActivity.this, ClockActivity.class);
            startActivity(intent);
        });
        countBtn.setOnClickListener(view -> {
            Intent intent = new Intent(DashboardActivity.this, DrinksCountActivity.class);
            startActivity(intent);
        });
        drinkawarenessBtn.setOnClickListener(view -> {
            Intent intent = new Intent(DashboardActivity.this, DrinkawareActivity.class);
            startActivity(intent);
        });
    }

    private void initializeUI() {
        checkListBtn = findViewById(R.id.checkListBtn);
        clockBtn = findViewById(R.id.clockBtn);
        countBtn = findViewById(R.id.countBtn);
        drinkawarenessBtn = findViewById(R.id.drinkawarenessBtn);
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
                break;
            case R.id.nav_map:
                Intent mapsIntent = new Intent(DashboardActivity.this, MapsActivity.class);
                startActivity(mapsIntent);
                break;
            case R.id.nav_timer:
                Intent clockIntent = new Intent(DashboardActivity.this, ClockActivity.class);
                startActivity(clockIntent);
                break;
            case R.id.nav_checklist:
                Intent checklistIntent = new Intent(DashboardActivity.this, ChecklistActivity.class);
                startActivity(checklistIntent);
                break;
            case R.id.nav_counter:
                Intent counterIntent = new Intent(DashboardActivity.this, DrinksCountActivity.class);
                startActivity(counterIntent);
                break;
            case R.id.nav_logout:
                Intent logoutIntent = new Intent(DashboardActivity.this, MainActivity.class);
                logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logoutIntent);
                FirebaseAuth.getInstance().signOut();
                break;
        }
        return true;
    }
}
