package com.ul.mobileappproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    private Button checkListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initializeUI();
        checkListBtn.setOnClickListener(view -> {
            Intent intent = new Intent(DashboardActivity.this,ChecklistActivity.class);
            startActivity(intent);
        });
    }

    private void initializeUI() {
        checkListBtn = findViewById(R.id.checkListBtn);
    }
}
