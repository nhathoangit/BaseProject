package appscyclone.com.base.dagger.module;

import android.app.Application;
import android.content.Context;

import appscyclone.com.base.ui.main.MainPresenterImp;
import dagger.Module;
import dagger.Provides;

/*
 * Created by NhatHoang on 14/05/2018.
 */
@Module
public class ApplicationModule {
    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    public MainPresenterImp provideMainPresenterImpl() {
        return new MainPresenterImp();
    }
}
