package com.example.jon.yisoufang2.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Jon on 2016/10/19.
 */
public class SharedPreferencesUtil {

    public static void setBoolean(Context context, final String key,
                                  final boolean value) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        settings.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, final String key,
                                     final boolean defaultValue) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        return settings.getBoolean(key, defaultValue);
    }

}
