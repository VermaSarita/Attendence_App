// MStash.java
package com.example.appointmentsystem.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class MStash {

    private static MStash mStash;
    private Context mContext;
    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mSharedPreferencesEditor;

    private MStash(Context context) {
        mContext = context;
        mSharedPreferences = context.getSharedPreferences("MyPreference", Context.MODE_PRIVATE);
        mSharedPreferencesEditor = mSharedPreferences.edit();
    }

    public static synchronized MStash getInstance(Context context) {
        if (mStash == null) {
            mStash = new MStash(context.getApplicationContext());
        }
        return mStash;
    }

    public void setValue(String key, String value) {
        mSharedPreferencesEditor.putString(key, value);
        mSharedPreferencesEditor.apply();
    }

    public void setValue(String key, Boolean value) {
        mSharedPreferencesEditor.putBoolean(key, value);
        mSharedPreferencesEditor.apply();
    }

    public void setValue(String key, Integer value) {
        mSharedPreferencesEditor.putInt(key, value);
        mSharedPreferencesEditor.apply();
    }

    public String getStringValue(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public Boolean getBooleanValue(String key, Boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public Integer getIntValue(String key, Integer defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    public void clear() {
        mSharedPreferencesEditor.clear().apply();
    }
}
