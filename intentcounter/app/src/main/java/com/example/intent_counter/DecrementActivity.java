// DecrementActivity.java
package com.example.intent_counter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DecrementActivity extends AppCompatActivity {
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrement);

        counter = getIntent().getIntExtra("current_count", 0);
        TextView counterText = findViewById(R.id.decrementCounterText);
        counterText.setText(String.valueOf(counter));

        Button confirmButton = findViewById(R.id.confirmDecrementButton);
        confirmButton.setOnClickListener(v -> {
            counter--;
            Intent resultIntent = new Intent();
            resultIntent.putExtra("updated_count", counter);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        Button cancelButton = findViewById(R.id.cancelDecrementButton);
        cancelButton.setOnClickListener(v -> finish());
    }
}