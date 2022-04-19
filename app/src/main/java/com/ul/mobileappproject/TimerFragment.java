package com.ul.mobileappproject;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.Locale;

public class TimerFragment extends Fragment {

    private final String START = "Start", PAUSE = "Pause", RESET = "Reset", RESUME = "Resume";
    View view;
    private CountDownTimer countDownTimer;
    private boolean mTimerRunning, started;
    private long mStartTimeInMillis, mTimeLeftInMillis;
    private EditText etMinutes, etSeconds;
    private Button startButton, resetButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timer, container, false);
        etMinutes = (EditText) view.findViewById(R.id.editTextMinutes);
        etSeconds = (EditText) view.findViewById(R.id.editTextSeconds);
        startButton = view.findViewById(R.id.timerStartButton);
        resetButton = view.findViewById(R.id.timerResetButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
        updateCountDownText();
        return view;
    }

    private void startTimer() {
        if(!started) {
            long minutes = Long.parseLong(etMinutes.getText().toString());
            long seconds = Long.parseLong(etSeconds.getText().toString());
            if(minutes > 59 || seconds > 59 || (minutes == 0 && seconds == 0)) {
                Toast.makeText(getActivity(), "Time Provided is Invalid", Toast.LENGTH_SHORT).show();
                mStartTimeInMillis = 1;
                return;
            }
            mStartTimeInMillis = minutes * 60000 + seconds * 1000;
            mTimeLeftInMillis = mStartTimeInMillis;
            started = true;
        }
        countDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                startButton.setText(START);
                startButton.setVisibility(View.INVISIBLE);
                resetButton.setVisibility(View.VISIBLE);
            }
        }.start();
        mTimerRunning = true;
        startButton.setText(PAUSE);
        resetButton.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        mTimerRunning = false;
        startButton.setText(RESUME);
        resetButton.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        mTimeLeftInMillis = mStartTimeInMillis;
        started = false;
        updateCountDownText();
        resetButton.setVisibility(View.INVISIBLE);
        startButton.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
        etMinutes.setText(String.format(Locale.getDefault(), "%02d", (long) ((mTimeLeftInMillis / 1000) / 60)));
        etSeconds.setText(String.format(Locale.getDefault(), "%02d", (long) ((mTimeLeftInMillis / 1000) % 60)));
    }

}
