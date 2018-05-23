package appscyclone.com.base.ui.base;

import android.app.Application;
import android.content.Context;

import appscyclone.com.base.config.AppConfig;
import appscyclone.com.base.dagger.component.ApplicationComponent;
import appscyclone.com.base.dagger.component.DaggerApplicationComponent;
import appscyclone.com.base.dagger.module.ApplicationModule;
import appscyclone.com.base.dagger.module.NetworkModule;

/*
 * Created by NhatHoang on 14/05/2018.
 */
public class BaseApplication extends Application {
    private ApplicationComponent mApplicationComponent;

    public static BaseApplication get(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(AppConfig.mConnectType))
                .build();
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }
}
