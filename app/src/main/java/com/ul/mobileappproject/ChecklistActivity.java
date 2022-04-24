package com.ul.mobileappproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ChecklistActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listViewItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("CheckList");
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        listViewItems = (ListView) findViewById(R.id.lvItems);
        readItems();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listViewItems.setAdapter(itemsAdapter);
        setUpListViewListener();
    }

    public void onAddItem(View view) {
        EditText editTextNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = editTextNewItem.getText().toString();
        itemsAdapter.add(itemText);
        editTextNewItem.setText("");
        writeItems();
    }

    private void setUpListViewListener() {
        listViewItems.setOnItemLongClickListener((adapter, view, pos, id) -> {
            items.remove(pos);
            itemsAdapter.notifyDataSetChanged();
            writeItems();
            return true;
        });
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException exception) {
            items = new ArrayList<>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
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
                Intent homeIntent = new Intent(ChecklistActivity.this, DashboardActivity.class);
                startActivity(homeIntent);
                break;
            case R.id.nav_timer:
                Intent timerIntent = new Intent(ChecklistActivity.this, ClockActivity.class);
                startActivity(timerIntent);
                break;
            case R.id.nav_checklist:
                break;
            case R.id.nav_counter:
                Intent counterIntent = new Intent(ChecklistActivity.this, DrinksCountActivity.class);
                startActivity(counterIntent);
                break;
            case R.id.nav_games:
                Intent gamesIntent = new Intent(ChecklistActivity.this, GameInstructionsActivity.class);
                startActivity(gamesIntent);
                break;
            case R.id.nav_drinkaware:
                Intent drinkawareIntent = new Intent(ChecklistActivity.this, DrinkawareActivity.class);
                startActivity(drinkawareIntent);
                break;
            case R.id.nav_logout:
                Intent logoutIntent = new Intent(ChecklistActivity.this, MainActivity.class);
                logoutIntent.setFlags(logoutIntent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logoutIntent);
                FirebaseAuth.getInstance().signOut();
                break;
            case R.id.nav_map:
                Intent mapsIntent = new Intent(ChecklistActivity.this, MapsActivity.class);
                startActivity(mapsIntent);
                break;
        }
        return true;
    }
}
