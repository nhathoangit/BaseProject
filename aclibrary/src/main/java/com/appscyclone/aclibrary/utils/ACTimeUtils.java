package com.appscyclone.aclibrary.utils;
/*
 * Created by HoangDong on 19/10/2017.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ACTimeUtils {
    private static final String FORMAT_TIME_FULL  = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final String FORMAT_TIME_NORMAL  = "yyyy/MM/dd HH:mm:ss";
    private static final String FORMAT_DATE  = "yyyy-MM-dd";
    private static final String FORMAT_TIME  = "HH:mm:ss";

    public static String getUtcTime(String date) {
        Date d = parseDate(date,FORMAT_TIME_NORMAL);
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_TIME_FULL, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(d);
    }

    public static String getUtcTime(String date,String formatOut) {
        Date d = parseDate(date,FORMAT_TIME_NORMAL);
        SimpleDateFormat sdf = new SimpleDateFormat(formatOut, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(d);
    }

    public static String getUtcTime(String date,String formatIn,String formatOut) {
        Date d = parseDate(date,formatIn);
        SimpleDateFormat sdf = new SimpleDateFormat(formatOut, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(d);
    }

    private static Date parseDate(String date,String format) {

        if (date == null) {
            return null;
        }
        StringBuilder sbDate = new StringBuilder();
        sbDate.append(date);
        String newDate = null;
        Date dateDT = null;

        try {
            newDate = sbDate.substring(0, 19);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String rDate = newDate.replace("T", " ");
        String nDate = rDate.replaceAll("-", "/");
        try {
            dateDT = new SimpleDateFormat(format, Locale.getDefault()).parse(nDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateDT;
    }
}
