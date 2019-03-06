package appscyclone.com.base.ui.main;

import appscyclone.com.base.R;
import appscyclone.com.base.ui.base.BaseActivity;
import appscyclone.com.base.ui.home.HomeFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        replaceFragment(new HomeFragment(), false, false);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        setUnBinder(ButterKnife.bind(this));
//        replaceFragment(new HomeFragment(), false, false);
//    }

}
