package appscyclone.com.base.ui.main;

import android.os.Bundle;

import javax.inject.Inject;

import appscyclone.com.base.R;
import appscyclone.com.base.data.network.api.StoryApi;
import appscyclone.com.base.data.network.model.ResStoryModel;
import appscyclone.com.base.ui.base.BaseActivity;
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
        setUnBinder(ButterKnife.bind(this));
        getActivityComponent().inject(this);
        presenter.onAttach(this);
    }

    @OnClick(R.id.actMain_btnTest)
    public void onViewClicked() {
        presenter.getListStories();
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
