package com.example.intent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class IncrementActivity extends AppCompatActivity {

    private int counter;
    private TextView textCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_increment);

        textCounter = findViewById(R.id.textCounter);
        Button btnIncrement = findViewById(R.id.btnIncrement);
        Button btnDecrement = findViewById(R.id.btnDecrement);
        Button btnBack = findViewById(R.id.btnBack);

        counter = getIntent().getIntExtra("counter", 0);
        updateCounter();

        btnIncrement.setOnClickListener(v -> {
            counter++;
            updateCounter();
        });

        btnDecrement.setOnClickListener(v -> {
            Intent intent = new Intent(IncrementActivity.this, DecrementActivity.class);
            intent.putExtra("counter", counter-1);
            startActivity(intent);
            finish(); // Close this activity when switching
        });

        btnBack.setOnClickListener(v -> returnToMain());
    }

    private void updateCounter() {
        textCounter.setText(String.valueOf(counter));
    }

    private void returnToMain() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("counter", counter);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
