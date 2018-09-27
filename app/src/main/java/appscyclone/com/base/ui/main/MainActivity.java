package appscyclone.com.base.ui.main;

import android.os.Bundle;

import appscyclone.com.base.R;
import appscyclone.com.base.ui.base.BaseActivity;
import appscyclone.com.base.ui.home.HomeFragment;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUnBinder(ButterKnife.bind(this));
        replaceFragment(new HomeFragment(), false, false);
    }

}
