package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int counter = 0;
    private TextView counterText;
    private TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterText = findViewById(R.id.counterText);
        statusText = findViewById(R.id.statusText);
        Button incrementButton = findViewById(R.id.incrementButton);
        Button decrementButton = findViewById(R.id.decrementButton);
        Button resetButton = findViewById(R.id.resetButton);

        incrementButton.setOnClickListener(v -> {
            counter++;
            updateUI("Incremented");
            Toast.makeText(this, "Counter increased to " + counter, Toast.LENGTH_SHORT).show();
        });

        decrementButton.setOnClickListener(v -> {
            counter--;
            updateUI("Decremented");
            Toast.makeText(this, "Counter decreased to " + counter, Toast.LENGTH_SHORT).show();
        });

        resetButton.setOnClickListener(v -> {
            counter = 0;
            updateUI("Reset");
            Toast.makeText(this, "Counter reset to 0", Toast.LENGTH_SHORT).show();
        });

        // Initial UI update
        updateUI("Initial State");
        Toast.makeText(this, "Welcome to Counter App!", Toast.LENGTH_SHORT).show();
    }

    private void updateUI(String status) {
        counterText.setText(String.valueOf(counter));
        statusText.setText(status);
    }
}