package appscyclone.com.base.activities;

import android.os.Bundle;

import appscyclone.com.base.R;
import appscyclone.com.base.bases.BaseActivity;
import appscyclone.com.base.fragments.FirstFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new FirstFragment(), true, false);
    }
}
