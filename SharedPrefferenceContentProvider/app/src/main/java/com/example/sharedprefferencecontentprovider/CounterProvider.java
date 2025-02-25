package com.example.sharedprefferencecontentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CounterProvider extends ContentProvider {

    private static final String AUTHORITY = "com.example.sharedprefferencecontentprovider.provider";
    private static final String PATH = "counter";
    private static final int COUNTER_CODE = 1;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, PATH, COUNTER_CODE);
    }

    private SharedPrefHelper sharedPrefHelper;

    @Override
    public boolean onCreate() {
        sharedPrefHelper = new SharedPrefHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        if (uriMatcher.match(uri) == COUNTER_CODE) {
            MatrixCursor cursor = new MatrixCursor(new String[]{"counter"});
            cursor.addRow(new Object[]{sharedPrefHelper.getCounter()});
            return cursor;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null; // No insert operation needed
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        if (uriMatcher.match(uri) == COUNTER_CODE && values != null) {
            String action = values.getAsString("action");

            if ("update".equals(action)) {
                // Increment/Decrement the counter
                int value = values.getAsInteger("value");
                sharedPrefHelper.updateCounter(value);
            } else if ("reset".equals(action)) {
                // Reset the counter to zero
                sharedPrefHelper.resetCounter();
            } else if ("set".equals(action)) {
                // Directly set the counter to a specific value
                int value = values.getAsInteger("value");
                sharedPrefHelper.setCounter(value);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0; // No delete operation needed
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
}
