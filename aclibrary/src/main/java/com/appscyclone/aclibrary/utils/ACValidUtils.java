package com.appscyclone.aclibrary.utils;
/*
 * Created by HoangDong on 21/09/2017.
 */

import android.util.Patterns;

public class ACValidUtils {
    public static boolean isURL(String url) {
        return Patterns.WEB_URL.matcher(url).matches();
    }

}
