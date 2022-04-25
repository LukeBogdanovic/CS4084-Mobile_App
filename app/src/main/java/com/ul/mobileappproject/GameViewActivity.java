package com.ul.mobileappproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class GameViewActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView descriptionTV;

    /**
     * Initializes the user interface elements from the elements in the xml file.
     * Initializes the navigation drawer user interface.
     * Sets the description textView with description passed using the intent.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameview);

        Intent intent = getIntent();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(intent.getStringExtra("Game"));
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        // Sets the description text after initializing with xml file elements.
        descriptionTV = findViewById(R.id.description_text);
        descriptionTV.setText(intent.getStringExtra("Description"));
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
     * Starts the activity of the selected menuItem from the navigation drawer.
     *
     * @param menuItem
     * @return boolean
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent homeIntent = new Intent(GameViewActivity.this, DashboardActivity.class);
                startActivity(homeIntent);
                break;
            case R.id.nav_timer:
                Intent clockIntent = new Intent(GameViewActivity.this, ClockActivity.class);
                startActivity(clockIntent);
                break;
            case R.id.nav_checklist:
                Intent checklistIntent = new Intent(GameViewActivity.this, ChecklistActivity.class);
                startActivity(checklistIntent);
                break;
            case R.id.nav_counter:
                Intent counterIntent = new Intent(GameViewActivity.this, DrinksCountActivity.class);
                startActivity(counterIntent);
                break;
            case R.id.nav_games:
                Intent gamesIntent = new Intent(GameViewActivity.this, GameInstructionsActivity.class);
                startActivity(gamesIntent);
                break;
            case R.id.nav_drinkaware:
                Intent drinkawareIntent = new Intent(GameViewActivity.this, DrinkawareActivity.class);
                startActivity(drinkawareIntent);
                break;
            case R.id.nav_logout:
                Intent logoutIntent = new Intent(GameViewActivity.this, MainActivity.class);
                logoutIntent.setFlags(logoutIntent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logoutIntent);
                FirebaseAuth.getInstance().signOut();
                break;
            case R.id.nav_map:
                Intent mapsIntent = new Intent(GameViewActivity.this, MapsActivity.class);
                startActivity(mapsIntent);
                break;
            case R.id.nav_profile:
                Intent profileIntent = new Intent(GameViewActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
                break;
        }
        return true;
    }
}
