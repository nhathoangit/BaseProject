package appscyclone.com.base.di.module;

import android.app.Activity;
import android.content.Context;

import appscyclone.com.base.di.ActivityScope;
import dagger.Module;
import dagger.Provides;

/*
 * Created by NhatHoang on 04/06/2018.
 */
@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Context provideContext() {
        return activity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity() {
        return activity;
    }

}
