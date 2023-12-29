package com.adamweb.sarcoapp;

import android.content.Context;
import android.content.SharedPreferences;

public class CounterUtil {
    private static final String PREF_NAME = "VisitCounterPref";
    private static final String KEY_VISIT_COUNT = "VisitCount";

    public static int getVisitCount(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getInt(KEY_VISIT_COUNT, 0);
    }

    public static void incrementVisitCount(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int currentCount = preferences.getInt(KEY_VISIT_COUNT, 0);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KEY_VISIT_COUNT, currentCount + 1);
        editor.apply();
    }
}
