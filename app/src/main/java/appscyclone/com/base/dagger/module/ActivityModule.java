package appscyclone.com.base.dagger.module;

import android.app.Activity;

import dagger.Module;

/*
 * Created by NhatHoang on 14/05/2018.
 */
@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

}
