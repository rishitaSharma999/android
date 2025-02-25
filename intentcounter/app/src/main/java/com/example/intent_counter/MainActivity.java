// MainActivity.java
package com.example.intent_counter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final int INCREMENT_REQUEST = 1;
    private static final int DECREMENT_REQUEST = 2;
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
            Intent intent = new Intent(MainActivity.this, IncrementActivity.class);
            intent.putExtra("current_count", counter);
            startActivityForResult(intent, INCREMENT_REQUEST);
        });

        decrementButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DecrementActivity.class);
            intent.putExtra("current_count", counter);
            startActivityForResult(intent, DECREMENT_REQUEST);
        });

        resetButton.setOnClickListener(v -> {
            counter = 0;
            updateUI("Reset");
            Toast.makeText(this, "Counter reset to 0", Toast.LENGTH_SHORT).show();
        });

        // Initial UI update
        updateUI("Initial State");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            counter = data.getIntExtra("updated_count", counter);
            String status = requestCode == INCREMENT_REQUEST ? "Incremented" : "Decremented";
            updateUI(status);
        }
    }

    private void updateUI(String status) {
        counterText.setText(String.valueOf(counter));
        statusText.setText(status);
    }
}

