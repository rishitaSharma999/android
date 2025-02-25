package com.example.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements
        IncrementFragment.OnIncrementListener,
        DecrementFragment.OnDecrementListener,
        ResetFragment.OnResetListener {

    private TextView counterTextView;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Loads activity_main.xml

        counterTextView = findViewById(R.id.counterTextView);
        updateCounter();

        // Load the three fragments into their respective containers defined in activity_main.xml
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerIncrement, new IncrementFragment())
                .commit();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerDecrement, new DecrementFragment())
                .commit();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerReset, new ResetFragment())
                .commit();
    }

    private void updateCounter() {
        counterTextView.setText(String.valueOf(counter));
    }

    @Override
    public void onIncrement() {
        counter++;
        updateCounter();
        // Change counter color to green when incremented
        counterTextView.setTextColor(Color.GREEN);
    }

    @Override
    public void onDecrement() {
        counter--;
        updateCounter();
        // Change counter color to red when decremented
        counterTextView.setTextColor(Color.RED);
    }

    @Override
    public void onReset() {
        counter = 0;
        updateCounter();
        // Change counter color to blue when reset
        counterTextView.setTextColor(Color.BLUE);
    }
}
