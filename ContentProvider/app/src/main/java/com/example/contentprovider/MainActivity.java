package com.example.contentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView counterText;
    private static final Uri CONTENT_URI = Uri.parse("content://com.example.sharedprefferencecontentprovider.provider/counter");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // ✅ Ensure this points to the correct layout

        counterText = findViewById(R.id.counterText);
        Button btnFetch = findViewById(R.id.btnFetch);

        btnFetch.setOnClickListener(v -> fetchCounterValue());
    }

    private void fetchCounterValue() {
        Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("counter");
            if (columnIndex != -1) {
                int counter = cursor.getInt(columnIndex);
                counterText.setText("Value from First App: " + counter);
            } else {
                counterText.setText("Column 'counter' not found");
            }
            cursor.close();
        } else {
            counterText.setText("Failed to fetch value");
        }
    }
}
