package appscyclone.com.base.ui.base;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import appscyclone.com.base.config.AppConfig;
import appscyclone.com.base.di.Injector;
import appscyclone.com.base.di.component.ApplicationComponent;

import appscyclone.com.base.di.component.DaggerApplicationComponent;
import appscyclone.com.base.di.module.ApplicationModule;
import appscyclone.com.base.di.module.NetworkModule;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/*
 * Created by NhatHoang on 14/05/2018.
 */
public class BaseApplication extends Application {

    @Inject
    CalligraphyConfig calligraphyConfig;
    private static BaseApplication mInstance;

    public static BaseApplication get(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }

    public static BaseApplication getApplication() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Injector.initialize(this);
        CalligraphyConfig.initDefault(calligraphyConfig);
    }

}
