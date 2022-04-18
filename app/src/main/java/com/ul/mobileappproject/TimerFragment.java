package com.ul.mobileappproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class TimerFragment extends Fragment {

    View view;
    private Button startButton, resetButton, stopButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timer, container, false);
        startButton = (Button) view.findViewById(R.id.timerStartButton);
        stopButton = (Button) view.findViewById(R.id.timerStopButton);
        resetButton = (Button) view.findViewById(R.id.timerResetButton);
        startButton.setOnClickListener(view -> Toast.makeText(getActivity(), "Timer Fragment", Toast.LENGTH_LONG).show());
        stopButton.setOnClickListener(view -> Toast.makeText(getActivity(), "Timer Fragment", Toast.LENGTH_LONG).show());
        resetButton.setOnClickListener(view -> Toast.makeText(getActivity(), "Timer Fragment", Toast.LENGTH_LONG).show());
        return view;
    }

}
