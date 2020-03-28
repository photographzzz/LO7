package com.photograph.lo7.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SharedPreferencesUtil {
    private static SharedPreferencesUtil sharedPreferencesUtil;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String FILENAME = "LO7";

    private SharedPreferencesUtil(Context context) {
        sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static SharedPreferencesUtil getInstance(Context context) {
        if (sharedPreferencesUtil == null) {
            synchronized (SharedPreferencesUtil.class) {
                if (sharedPreferencesUtil == null) {
                    sharedPreferencesUtil = new SharedPreferencesUtil(context);
                }
            }
        }
        return sharedPreferencesUtil;
    }

    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public Object getObject(String key,Class c) {
        String str = sharedPreferences.getString(key, "");
        Gson gson = new Gson();
        return gson.fromJson(str, c);
    }

    public void delete(String key) {
        editor.remove(key).commit();
    }

    public void clear(){
        editor.clear().commit();
    }

}
