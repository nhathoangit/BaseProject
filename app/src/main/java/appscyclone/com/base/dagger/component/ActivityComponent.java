package appscyclone.com.base.dagger.component;

/*
 * Created by NhatHoang on 14/05/2018.
 */

import appscyclone.com.base.dagger.PerActivity;
import appscyclone.com.base.dagger.module.ActivityModule;
import appscyclone.com.base.ui.main.MainActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
}
