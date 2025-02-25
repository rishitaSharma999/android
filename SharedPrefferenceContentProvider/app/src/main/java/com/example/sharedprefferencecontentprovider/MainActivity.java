package com.example.sharedprefferencecontentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etCounter;
    private TextView tvCurrentCounter;
    private ContentResolver contentResolver;
    private static final Uri CONTENT_URI = Uri.parse("content://com.example.sharedprefferencecontentprovider.provider/counter");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCounter = findViewById(R.id.etCounter);
        tvCurrentCounter = findViewById(R.id.tvCurrentCounter);
        Button btnSave = findViewById(R.id.btnSave);

        contentResolver = getContentResolver();

        btnSave.setOnClickListener(v -> saveCounterValue());
        updateCounterUI();  // Display the current value
    }

    private void saveCounterValue() {
        String value = etCounter.getText().toString();
        if (!value.isEmpty()) {
            int enteredValue = Integer.parseInt(value);

            ContentValues values = new ContentValues();
            values.put("action", "set");
            values.put("value", enteredValue);

            contentResolver.update(CONTENT_URI, values, null, null);
            updateCounterUI();  // Update displayed value
        }
    }

    private void updateCounterUI() {
        Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int counter = cursor.getInt(cursor.getColumnIndex("counter"));
            tvCurrentCounter.setText("Current Value: " + counter);
            cursor.close();
        }
    }
}
