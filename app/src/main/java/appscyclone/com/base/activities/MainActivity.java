package appscyclone.com.base.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import appscyclone.com.base.R;
import appscyclone.com.base.bases.BaseActivity;
import appscyclone.com.base.utils.KeyboardUtils;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        KeyboardUtils.setupUI(getWindow().getDecorView().getRootView(),this);
    }
}
