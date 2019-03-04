package appscyclone.com.base.utils;
/*
 * Created by Thanh.Tran on 25/07/2017.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class SharedPrefUtils {

    public static String getString(Context context, String key) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return preferences.getString(key, "");
    }

    public static void setString(Context context, String key,
                                 String content) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context)
                .edit();
        edit.putString(key, content);
        edit.apply();
    }

    public static void setInt(Context context, String key, int content) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putInt(key, content);
        edit.apply();
    }

    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(key, defaultValue);
    }

    public static int getInt(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(key, 0);
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(key, defaultValue);
    }

    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    public static boolean getBooleanTypeTask(Context context, String key) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return preferences.getBoolean(key, true);
    }

    public static void setBoolean(Context context, String key,
                                  boolean content) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context)
                .edit();
        edit.putBoolean(key, content);
        edit.apply();
    }

    public static void remove(Context context, String key) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context)
                .edit();
        edit.remove(key);
        edit.apply();
    }

}
