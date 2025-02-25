package com.example.intent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView counterText;
    private int counter = 0;

    private final ActivityResultLauncher<Intent> activityLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            counter = result.getData().getIntExtra("counter", counter);
                            updateCounter();
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterText = findViewById(R.id.counterText);
        Button btnIncrement = findViewById(R.id.btnIncrement);
        Button btnDecrement = findViewById(R.id.btnDecrement);

        updateCounter();

        btnIncrement.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, IncrementActivity.class);
            intent.putExtra("counter", counter+1);
            activityLauncher.launch(intent);
        });

        btnDecrement.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DecrementActivity.class);
            intent.putExtra("counter", counter-1);
            activityLauncher.launch(intent);
        });
    }

    private void updateCounter() {
        counterText.setText(String.valueOf(counter));
    }
}
