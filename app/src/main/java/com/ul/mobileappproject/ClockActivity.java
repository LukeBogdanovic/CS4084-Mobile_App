package com.ul.mobileappproject;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ClockActivity extends AppCompatActivity {

    Button timerFragment, stopwatchFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

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

}
