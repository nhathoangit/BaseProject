package appscyclone.com.base.ui.main;

import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import appscyclone.com.base.R;
import appscyclone.com.base.data.network.api.StoryApi;
import appscyclone.com.base.data.network.model.ResStoryModel;
import appscyclone.com.base.ui.base.BaseActivity;
import appscyclone.com.base.ui.base.BaseApplication;
import appscyclone.com.base.ui.base.BaseFragment;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainContract.MainView {

    @Inject
    MainPresenterImp presenter;
    @Inject
    StoryApi storyApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        BaseApplication.get(this).getComponent().inject(this);
        presenter.onAttach(this);
    }

    @OnClick(R.id.actMain_btnTest)
    public void onViewClicked() {
       presenter.getListStories(storyApi);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    public void loadListStories(ResStoryModel resStoryModel) {

    }

}
