package appscyclone.com.base.utils;

import android.content.Context;

import appscyclone.com.base.others.dialog.MessageDialog;
import appscyclone.com.base.others.interfaces.ConfirmListener;
import appscyclone.com.base.others.interfaces.YesNoListener;

/*
 * Created by NhatHoang on 21/05/2018.
 */
public class AppUtils {

    public static void showDialogMessage(Context context, String title, String content, ConfirmListener confirmListener) {
        MessageDialog dialog = new MessageDialog(context, title, content, confirmListener);
        dialog.show();
    }

}
