package com.example.sharedprefferencecontentprovider;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefHelper {
    private static final String PREF_NAME = "counter_prefs";
    private static final String KEY_COUNTER = "counter";
    private final SharedPreferences sharedPreferences;

    public SharedPrefHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public int getCounter() {
        return sharedPreferences.getInt(KEY_COUNTER, 0);
    }

    public void updateCounter(int value) {
        int current = getCounter();
        sharedPreferences.edit().putInt(KEY_COUNTER, current + value).apply();
    }

    public void resetCounter() {
        sharedPreferences.edit().putInt(KEY_COUNTER, 0).apply();
    }

    // ✅ New method to directly set a specific counter value
    public void setCounter(int value) {
        sharedPreferences.edit().putInt(KEY_COUNTER, value).apply();
    }
}
