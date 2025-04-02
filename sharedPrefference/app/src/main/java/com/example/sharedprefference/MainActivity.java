package com.example.sharedprefference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView ans, savedValue;
    private int count = 0;
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "CounterPrefs";
    private static final String KEY_COUNT = "count_value";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ans = findViewById(R.id.ans);
        savedValue = findViewById(R.id.saved_value);
        ImageButton inc = findViewById(R.id.inc);
        ImageButton dec = findViewById(R.id.dec);
        Button reset = findViewById(R.id.reset);
        Button save = findViewById(R.id.save);
        Button retrieve = findViewById(R.id.retrieve);

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        inc.setOnClickListener(view -> {
            count++;
            updateCounter();
        });

        dec.setOnClickListener(view -> {
            count--;
            updateCounter();
        });

        reset.setOnClickListener(view -> {
            count = 0;
            updateCounter();
        });

        save.setOnClickListener(view -> {
            saveCounter();
        });

        retrieve.setOnClickListener(view -> {
            retrieveCounter();
        });

        retrieveCounter();
    }

    private void updateCounter() {
        ans.setText(String.valueOf(count));
    }

    private void saveCounter() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_COUNT, count);
        editor.apply();
        savedValue.setText("Saved Value: " + count);
    }

    private void retrieveCounter() {
        int savedCount = sharedPreferences.getInt(KEY_COUNT, 0);
        savedValue.setText("Saved Value: " + savedCount);
    }
}