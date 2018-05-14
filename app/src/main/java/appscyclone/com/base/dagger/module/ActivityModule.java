package appscyclone.com.base.dagger.module;

import android.app.Activity;
import android.content.Context;

import appscyclone.com.base.dagger.ActivityContext;
import appscyclone.com.base.dagger.PerActivity;
import appscyclone.com.base.ui.main.MainPresenterImp;
import dagger.Module;
import dagger.Provides;

/*
 * Created by NhatHoang on 14/05/2018.
 */
@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @PerActivity
    public MainPresenterImp provideMainPresenterImpl() {
        return new MainPresenterImp();
    }

}
