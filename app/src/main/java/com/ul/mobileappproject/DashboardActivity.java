package com.ul.mobileappproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    /**
     * Initializes the user interface elements from the elements in the xml file.
     * Initializes the navigation drawer user interface.
     * Initializes the Recycler view and sets the adapter for the recycler view.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // Sets the adapter to use the posts ArrayList
        final DashboardAdapter adapter = new DashboardAdapter(this, getPosts());
        recyclerView.setAdapter(adapter);
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
                break;
            case R.id.nav_profile:
                Intent profileIntent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
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
            case R.id.nav_games:
                Intent gamesIntent = new Intent(DashboardActivity.this, GameInstructionsActivity.class);
                startActivity(gamesIntent);
                break;
            case R.id.nav_drinkaware:
                Intent drinkawareIntent = new Intent(DashboardActivity.this, DrinkawareActivity.class);
                startActivity(drinkawareIntent);
                break;
            case R.id.nav_logout:
                Intent logoutIntent = new Intent(DashboardActivity.this, MainActivity.class);
                logoutIntent.setFlags(logoutIntent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logoutIntent);
                FirebaseAuth.getInstance().signOut();
                break;
        }
        return true;
    }

    /**
     * Creates post objects.
     * Adds post objects into an ArrayList and returns the arraylist to calling function.
     * @return ArrayList
     */
    public ArrayList<Post> getPosts() {
        ArrayList<Post> posts = new ArrayList<>();

        Post post = new Post("Costello", "Guinness ???3.50 all day Thursday", "https://www.limerick.ie/sites/default/files/styles/hero_image/public/media/images/2019-08/Costello%27s%20%20Tavern%201%20810x456.jpg?itok=BQLA2ugG");
        posts.add(post);

        post = new Post("Angel Lane", "3 Jaeger bombs for ???10 Tuesday night", "https://www.limerick.ie/sites/default/files/styles/hero_image/public/media/images/2017-10/Angel%20Lane%20Nightclub%20810x456.jpg?itok=KdvGXf55");
        posts.add(post);

        post = new Post("Stables", "Jersey Night Tuesday, wear your County Colours", "https://ulmembersclubs.wolves.ie/assets/img/logos/stables.jpg");
        posts.add(post);

        post = new Post("Hurlers", "Table quiz Wednesday at 6pm", "https://lh3.googleusercontent.com/p/AF1QipNrtb1Sk04FxNv6Qw8sfSNR6Y286nBnM9cIpZzE=w1080-h608-p-no-v0");
        posts.add(post);

        return posts;
    }
}
