package appscyclone.com.base.dagger.component;

import javax.inject.Singleton;

import appscyclone.com.base.dagger.module.ApplicationModule;
import appscyclone.com.base.ui.base.BaseApplication;
import dagger.Component;

/*
 * Created by NhatHoang on 14/05/2018.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseApplication app);
}
