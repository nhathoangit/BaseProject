package com.appscyclone.aclibrary.utils;
/*
 * Created by HoangDong on 21/09/2017.
 */

import android.content.Context;

public class ACConvertUtils {
    public static float fromDpToPx(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}
