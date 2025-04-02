package com.example.contentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView counterText;
    private Button btnFetch, btnIncrement, btnDecrement;
    private int counterValue = 0; // Store the fetched value locally

    private static final Uri CONTENT_URI = Uri.parse("content://com.example.sharedprefferencecontentprovider.provider/counter");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterText = findViewById(R.id.counterText);
        btnFetch = findViewById(R.id.btnFetch);
        btnIncrement = findViewById(R.id.btnIncrement);
        btnDecrement = findViewById(R.id.btnDecrement);

        btnFetch.setOnClickListener(v -> fetchCounterValue());
        btnIncrement.setOnClickListener(v -> incrementCounter());
        btnDecrement.setOnClickListener(v -> decrementCounter());
    }

    private void fetchCounterValue() {
        Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("counter");
            if (columnIndex != -1) {
                counterValue = cursor.getInt(columnIndex);
                counterText.setText("Value from First App: " + counterValue);
            } else {
                counterText.setText("Column 'counter' not found");
            }
            cursor.close();
        } else {
            counterText.setText("Failed to fetch value");
        }
    }

    private void incrementCounter() {
        counterValue++;
        counterText.setText("Value from First App: " + counterValue);
    }

    private void decrementCounter() {
        counterValue--;
        counterText.setText("Value from First App: " + counterValue);
    }
}
