package appscyclone.com.base.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;

import appscyclone.com.base.interfaces.DialogListener;
import appscyclone.com.base.others.dialog.AlertCustomDialog;

/*
 * Created by NhatHoang on 20/04/2018.
 */
public class AppUtils {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = null;
        if (cm != null) {
            netInfo = cm.getActiveNetworkInfo();
        }
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static void showAlert(final Context context, String title, String content, @Nullable final DialogListener listener) {
        AlertCustomDialog alertDialog = new AlertCustomDialog(context, title, content, listener);
        alertDialog.show();
    }

}
