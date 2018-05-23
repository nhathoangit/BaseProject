package appscyclone.com.base.dagger.component;

import javax.inject.Singleton;

import appscyclone.com.base.dagger.AppScope;
import appscyclone.com.base.dagger.module.ApplicationModule;
import appscyclone.com.base.dagger.module.NetworkModule;
import appscyclone.com.base.ui.base.BaseActivity;
import appscyclone.com.base.ui.base.BaseApplication;
import appscyclone.com.base.ui.main.MainActivity;
import dagger.Component;

/*
 * Created by NhatHoang on 14/05/2018.
 */
@AppScope
@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);
}
