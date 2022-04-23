package com.ul.mobileappproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    private Button checkListBtn, clockBtn, countBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
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
    }

    private void initializeUI() {
        checkListBtn = findViewById(R.id.checkListBtn);
        clockBtn = findViewById(R.id.clockBtn);
        countBtn = findViewById(R.id.countBtn);
    }
}
