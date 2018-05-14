package appscyclone.com.base.ui.main;

import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import appscyclone.com.base.R;
import appscyclone.com.base.ui.base.BaseActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainContract.MainView {

    @Inject
    MainPresenterImp presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        presenter.onAttach(this);
    }

    @OnClick(R.id.actMain_btnTest)
    public void onViewClicked() {
        presenter.ToastMess("aaaaa");
    }

    @Override
    public void ToastMess(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
