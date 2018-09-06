package com.appscyclone.aclibrary.utils;
/*
 * Created by HoangDong on 21/09/2017.
 */

import android.content.res.Resources;

public class ACGetUtils {
    public static int getScreenHalfWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels / 2;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
}
