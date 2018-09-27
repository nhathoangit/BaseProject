package appscyclone.com.base.ui.main;

import javax.inject.Inject;

import appscyclone.com.base.data.network.api.StoryApi;
import appscyclone.com.base.data.network.model.ResStoryModel;
import appscyclone.com.base.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/*
 * Created by NhatHoang on 14/05/2018.
 */
public class MainPresenterImp extends BasePresenter<MainContract.MainView> implements MainContract.MainPresenter{

    private StoryApi storyApi;

    @Inject
    MainPresenterImp(StoryApi storyApi) {
        this.storyApi = storyApi;
    }

    @Override
    public void getListStories() {
        addSubscribe(storyApi.getStories(1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResStoryModel>() {
            @Override
            public void accept(ResStoryModel resStoryModel) throws Exception {
                getView().loadListStories(resStoryModel);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }));
    }
}
