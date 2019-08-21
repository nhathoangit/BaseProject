package appscyclone.com.base.di.module;

import android.app.Application;

import javax.inject.Singleton;

import appscyclone.com.base.ui.base.BaseApplication;
import dagger.Module;
import dagger.Provides;

/*
 * Created by NhatHoang on 14/05/2018.
 */
@Module
public class ApplicationModule {
    private final BaseApplication application;

    public ApplicationModule(BaseApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return this.application;
    }

}
