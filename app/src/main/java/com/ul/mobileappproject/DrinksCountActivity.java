package com.ul.mobileappproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class DrinksCountActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private Button wine, beer, spirit, cider, stout, units;
    private TextView wineCount, beerCount, spiritCount, ciderCount, stoutCount, unitsCount;
    private int wineBtnCount = 0, beerBtnCount = 0, spiritBtnCount = 0, ciderBtnCount = 0, stoutBtnCount = 0, unitsBtnCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinkscount);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Drinks Counter");
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        wine = findViewById(R.id.wine);
        wineCount = findViewById(R.id.wineCount);

        beer = findViewById(R.id.beer);
        beerCount = findViewById(R.id.beerCount);

        spirit = findViewById(R.id.spirit);
        spiritCount = findViewById(R.id.spiritCount);

        cider = findViewById(R.id.cider);
        ciderCount = findViewById(R.id.ciderCount);

        stout = findViewById(R.id.stout);
        stoutCount = findViewById(R.id.stoutCount);

        units = findViewById(R.id.units);
        unitsCount = findViewById(R.id.unitCount);
    }

    @SuppressLint("SetTextI18n")
    public void resetCount(View view) {
        wineBtnCount = 0;
        beerBtnCount = 0;
        spiritBtnCount = 0;
        ciderBtnCount = 0;
        stoutBtnCount = 0;
        unitsBtnCount = 0;
        wineCount.setText(Integer.toString(wineBtnCount));
        beerCount.setText(Integer.toString(beerBtnCount));
        spiritCount.setText(Integer.toString(spiritBtnCount));
        ciderCount.setText(Integer.toString(ciderBtnCount));
        stoutCount.setText(Integer.toString(stoutBtnCount));
        unitsCount.setText(Integer.toString(unitsBtnCount));
    }

    @SuppressLint("SetTextI18n")
    public void wineCount(View view) {
        wineBtnCount++;
        unitsBtnCount++;
        wineCount.setText(Integer.toString(wineBtnCount));
        unitsCount.setText(Integer.toString(unitsBtnCount));
    }

    @SuppressLint("SetTextI18n")
    public void beerCount(View view) {
        beerBtnCount++;
        unitsBtnCount++;
        beerCount.setText(Integer.toString(beerBtnCount));
        unitsCount.setText(Integer.toString(unitsBtnCount));
    }

    @SuppressLint("SetTextI18n")
    public void spiritCount(View view) {
        spiritBtnCount++;
        unitsBtnCount++;
        spiritCount.setText(Integer.toString(spiritBtnCount));
        unitsCount.setText(Integer.toString(unitsBtnCount));
    }

    @SuppressLint("SetTextI18n")
    public void ciderCount(View view) {
        ciderBtnCount++;
        unitsBtnCount++;
        ciderCount.setText(Integer.toString(ciderBtnCount));
        unitsCount.setText(Integer.toString(unitsBtnCount));
    }

    @SuppressLint("SetTextI18n")
    public void stoutCount(View view) {
        stoutBtnCount++;
        unitsBtnCount++;
        stoutCount.setText(Integer.toString(stoutBtnCount));
        unitsCount.setText(Integer.toString(unitsBtnCount));
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
                Intent homeIntent = new Intent(DrinksCountActivity.this, DashboardActivity.class);
                startActivity(homeIntent);
                break;
            case R.id.nav_profile:
                Intent profileIntent = new Intent(DrinksCountActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
                break;
            case R.id.nav_timer:
                Intent timerIntent = new Intent(DrinksCountActivity.this, ClockActivity.class);
                startActivity(timerIntent);
                break;
            case R.id.nav_checklist:
                Intent checklistIntent = new Intent(DrinksCountActivity.this, ChecklistActivity.class);
                startActivity(checklistIntent);
                break;
            case R.id.nav_counter:
                break;
            case R.id.nav_games:
                Intent gamesIntent = new Intent(DrinksCountActivity.this, GameInstructionsActivity.class);
                startActivity(gamesIntent);
                break;
            case R.id.nav_drinkaware:
                Intent drinkawareIntent = new Intent(DrinksCountActivity.this, DrinkawareActivity.class);
                startActivity(drinkawareIntent);
                break;
            case R.id.nav_logout:
                Intent logoutIntent = new Intent(DrinksCountActivity.this, MainActivity.class);
                logoutIntent.setFlags(logoutIntent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logoutIntent);
                FirebaseAuth.getInstance().signOut();
                break;
            case R.id.nav_map:
                Intent mapsIntent = new Intent(DrinksCountActivity.this, MapsActivity.class);
                startActivity(mapsIntent);
                break;
        }
        return true;
    }
}
