package com.ul.mobileappproject;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class StopWatchFragment extends Fragment {

    private final int REFRESH_RATE = 100;
    View view;
    Button buttonStart, buttonStop, buttonReset;
    TextView timer, timerMs;
    private boolean stopped = false;
    private long startTime, elapsedTime, secs, mins, hrs, mSecs;
    private String hours, minutes, seconds, milliSeconds;
    private Handler mHandler = new Handler();
    /**
     * Runnable that calculates elapsed Time since timer start.
     * Calls the update time function and passes the elapsedTime.
     */
    private Runnable startTimer = new Runnable() {
        @Override
        public void run() {
            elapsedTime = System.currentTimeMillis() - startTime;
            updateTimer(elapsedTime);
            mHandler.postDelayed(this, REFRESH_RATE);
        }
    };

    /**
     * Initializes the User interface elements with the elements from the xml file.
     * Sets listeners for the start/resume,stop and reset buttons
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        buttonStart = (Button) view.findViewById(R.id.buttonStart);
        buttonStop = (Button) view.findViewById(R.id.buttonStop);
        buttonReset = (Button) view.findViewById(R.id.buttonReset);
        timer = (TextView) view.findViewById(R.id.timer);
        timerMs = (TextView) view.findViewById(R.id.timerMs);
        // Starts the timer
        buttonStart.setOnClickListener(v -> {
            showStopButton();
            if (stopped) {
                startTime = System.currentTimeMillis() - elapsedTime;
            } else {
                startTime = System.currentTimeMillis();
            }
            mHandler.removeCallbacks(startTimer);
            mHandler.postDelayed(startTimer, 0);
        });
        // Stops the timer
        buttonStop.setOnClickListener(v -> {
            hideStopButton();
            mHandler.removeCallbacks(startTimer);
            stopped = true;
        });
        // Resets the displayed time to 0
        buttonReset.setOnClickListener(v -> {
            stopped = false;
            ((TextView) view.findViewById(R.id.timer)).setText("00:00:00");
            ((TextView) view.findViewById(R.id.timerMs)).setText(".0");
        });
        return view;
    }

    /**
     * Shows the stop Button.
     * Hides the start/resume and reset buttons.
     */
    private void showStopButton() {
        view = getView();
        ((Button) view.findViewById(R.id.buttonStart)).setVisibility(View.GONE);
        ((Button) view.findViewById(R.id.buttonReset)).setVisibility(View.GONE);
        ((Button) view.findViewById(R.id.buttonStop)).setVisibility(View.VISIBLE);
    }

    /**
     *  Hides the stop button.
     *  Displays the start/resume and reset buttons.
     */
    private void hideStopButton() {
        view = getView();
        ((Button) view.findViewById(R.id.buttonStart)).setVisibility(View.VISIBLE);
        ((Button) view.findViewById(R.id.buttonReset)).setVisibility(View.VISIBLE);
        ((Button) view.findViewById(R.id.buttonStop)).setVisibility(View.GONE);
    }

    /**
     * Calculates the time since start in seconds,minutes,hours and milliseconds.
     * Updates the time on the User Interface since start with the
     * calculated seconds,minutes, hours and milliseconds.
     * @param time
     */
    private void updateTimer(float time) {
        view = getView();
        secs = (long) (time / 1000);
        mins = (long) ((time / 1000) / 60);
        hrs = (long) (((time / 1000) / 60) / 60);
        secs = secs % 60;
        seconds = String.valueOf(secs);
        if (secs == 0) {
            seconds = "00";
        }
        if (secs < 10 && secs > 0) {
            seconds = "0" + seconds;
        }

        mins = mins % 60;
        minutes = String.valueOf(mins);
        if (mins == 0) {
            minutes = "00";
        }
        if (mins < 10 && mins > 0) {
            minutes = "0" + minutes;
        }

        hours = String.valueOf(hrs);
        if (hrs == 0) {
            hours = "00";
        }
        if (hrs < 10 && hrs > 0) {
            hours = "0" + hours;
        }

        milliSeconds = String.valueOf((long) time);
        if (milliSeconds.length() == 2) {
            milliSeconds = "0" + milliSeconds;
        }
        if (milliSeconds.length() <= 1) {
            milliSeconds = "00";
        }
        milliSeconds = milliSeconds.substring(milliSeconds.length() - 3, milliSeconds.length() - 2);
        timer.setText(hours + ":" + minutes + ":" + seconds);
        timerMs.setText("." + milliSeconds);
    }

}
