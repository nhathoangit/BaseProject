package appscyclone.com.base.ui.base;

import android.app.Application;
import android.content.Context;

import appscyclone.com.base.dagger.component.ApplicationComponent;
import appscyclone.com.base.dagger.component.DaggerApplicationComponent;
import appscyclone.com.base.dagger.module.ApplicationModule;

/*
 * Created by NhatHoang on 14/05/2018.
 */
public class BaseApplication extends Application {
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);
    }

    public static BaseApplication get(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }
}
