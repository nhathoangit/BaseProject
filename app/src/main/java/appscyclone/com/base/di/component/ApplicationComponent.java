package appscyclone.com.base.di.component;

import javax.inject.Singleton;

import appscyclone.com.base.di.AppScope;
import appscyclone.com.base.di.module.ApplicationModule;
import appscyclone.com.base.di.module.NetworkModule;
import dagger.Component;

/*
 * Created by NhatHoang on 14/05/2018.
 */
@AppScope
@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    ActivityComponent inject();

}
