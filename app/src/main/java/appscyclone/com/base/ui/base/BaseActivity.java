package appscyclone.com.base.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import appscyclone.com.base.dagger.component.ActivityComponent;
import appscyclone.com.base.dagger.component.DaggerActivityComponent;
import appscyclone.com.base.dagger.module.ActivityModule;

/*
 * Created by NhatHoang on 14/05/2018.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(BaseApplication.get(this).getComponent())
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }
}
