package com.ul.mobileappproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    private Button checkListBtn, clockBtn,gameInstructionBtn;

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
        gameInstructionBtn.setOnClickListener(view -> {
            Intent intent = new Intent(DashboardActivity.this, GameInstructionsActivity.class);
            startActivity(intent);
        });
    }

    private void initializeUI() {
        checkListBtn = findViewById(R.id.checkListBtn);
        clockBtn = findViewById(R.id.clockBtn);
        gameInstructionBtn = findViewById(R.id.gameInstructionBtn);
    }
}
