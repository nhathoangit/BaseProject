package appscyclone.com.base.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import appscyclone.com.base.ui.main.MainActivity;

/**
 * Created by TDP on 24/04/2018.
 */

public class StartActivityUtils {
    public static void toMain(Context context, Bundle bundle) {
        Intent intent = new Intent().setClass(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (bundle != null)
            intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
