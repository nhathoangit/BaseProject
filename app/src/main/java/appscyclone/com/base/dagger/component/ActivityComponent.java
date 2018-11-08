package appscyclone.com.base.dagger.component;

import appscyclone.com.base.dagger.ActivityScope;
import appscyclone.com.base.dagger.module.ActivityModule;
import appscyclone.com.base.ui.expandview.ExpandFragment;
import appscyclone.com.base.ui.headerfooter.HFFragment;
import appscyclone.com.base.ui.home.HomeFragment;
import appscyclone.com.base.ui.main.MainActivity;
import appscyclone.com.base.ui.multipleviewtype.MultipleTypeFragment;
import dagger.Subcomponent;

/*
 * Created by NhatHoang on 04/06/2018.
 */

@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(HomeFragment homeFragment);

    void inject(MultipleTypeFragment multipleTypeFragment);

    void inject(HFFragment hfFragment);

    void inject(ExpandFragment expandFragment);

}
