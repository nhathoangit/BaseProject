package appscyclone.com.base.bases;

import android.app.Application;

/*
 * Created by NhatHoang on 20/04/2018.
 */
public class BaseApplication extends Application {
    private boolean isCancelAnimation;

    public boolean getCancelAnimation() {
        return isCancelAnimation;
    }
}
