package appscyclone.com.base.di;

import appscyclone.com.base.di.component.ApplicationComponent;
import appscyclone.com.base.di.component.DaggerApplicationComponent;
import appscyclone.com.base.di.module.ApplicationModule;
import appscyclone.com.base.ui.base.BaseApplication;

/*
 * Created by NhatHoang on 12/08/2019.
 */
public enum Injector {
    INSTANCE;

    ApplicationComponent applicationComponent;

    private Injector(){
    }

    public static void initialize(BaseApplication baseApplication) {
        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(baseApplication))
                .build();
        INSTANCE.applicationComponent = applicationComponent;
    }

    public static ApplicationComponent get() {
        return INSTANCE.applicationComponent;
    }
}
