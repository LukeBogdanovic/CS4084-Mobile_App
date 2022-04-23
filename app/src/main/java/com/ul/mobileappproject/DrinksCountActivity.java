package com.ul.mobileappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DrinksCountActivity extends AppCompatActivity {

    Button wine;
    TextView wineCount;
    int wineBtnCount = 0;

    Button beer;
    TextView beerCount;
    int beerBtnCount = 0;

    Button spirit;
    TextView spiritCount;
    int spiritBtnCount = 0;

    Button cider;
    TextView ciderCount;
    int ciderBtnCount = 0;

    Button stout;
    TextView stoutCount;
    int stoutBtnCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinkscount);

        wine = findViewById(R.id.wine);
        wineCount = findViewById(R.id.wineCount);

        beer = findViewById(R.id.beer);
        beerCount = findViewById(R.id.beerCount);

        spirit = findViewById(R.id.spirit);
        spiritCount = findViewById(R.id.spiritCount);

        cider = findViewById(R.id.cider);
        ciderCount = findViewById(R.id.ciderCount);

        stout = findViewById(R.id.stout);
        stoutCount = findViewById(R.id.stoutCount);
    }

    @SuppressLint("SetTextI18n")
    public void resetCount (View view) {
        wineBtnCount = 0;
        beerBtnCount = 0;
        spiritBtnCount = 0;
        ciderBtnCount = 0;
        stoutBtnCount = 0;
        wineCount.setText(Integer.toString(wineBtnCount));
        beerCount.setText(Integer.toString(beerBtnCount));
        spiritCount.setText(Integer.toString(spiritBtnCount));
        ciderCount.setText(Integer.toString(ciderBtnCount));
        stoutCount.setText(Integer.toString(stoutBtnCount));
    }

    @SuppressLint("SetTextI18n")
    public void wineCount(View view) {
        wineBtnCount++;
        wineCount.setText(Integer.toString(wineBtnCount));
    }

    @SuppressLint("SetTextI18n")
    public void beerCount(View view) {
        beerBtnCount++;
        beerCount.setText(Integer.toString(beerBtnCount));
    }

    @SuppressLint("SetTextI18n")
    public void spiritCount(View view) {
        spiritBtnCount++;
        spiritCount.setText(Integer.toString(spiritBtnCount));
    }

    @SuppressLint("SetTextI18n")
    public void ciderCount(View view) {
        ciderBtnCount++;
        ciderCount.setText(Integer.toString(ciderBtnCount));
    }

    @SuppressLint("SetTextI18n")
    public void stoutCount(View view) {
        stoutBtnCount++;
        stoutCount.setText(Integer.toString(stoutBtnCount));
    }

}
