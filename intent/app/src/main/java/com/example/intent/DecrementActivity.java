package com.example.intent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DecrementActivity extends AppCompatActivity {

    private int counter;
    private TextView textCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrement);

        textCounter = findViewById(R.id.textCounter);
        Button btnDecrement = findViewById(R.id.btnDecrement);
        Button btnIncrement = findViewById(R.id.btnIncrement);
        Button btnBack = findViewById(R.id.btnBack);

        counter = getIntent().getIntExtra("counter", 0);
        updateCounter();

        btnDecrement.setOnClickListener(v -> {
            counter--;
            updateCounter();
        });

        btnIncrement.setOnClickListener(v -> {
            Intent intent = new Intent(DecrementActivity.this, IncrementActivity.class);
            intent.putExtra("counter", counter+1);
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
