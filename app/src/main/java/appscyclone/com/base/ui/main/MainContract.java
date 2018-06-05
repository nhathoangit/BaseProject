package appscyclone.com.base.ui.main;

import appscyclone.com.base.data.network.api.StoryApi;
import appscyclone.com.base.data.network.model.ResStoryModel;
import appscyclone.com.base.ui.base.MvpView;

/*
 * Created by NhatHoang on 14/05/2018.
 */
public interface MainContract {
    interface MainView extends MvpView {
        void loadListStories(ResStoryModel resStoryModel);
    }

    interface MainPresenter {
        void getListStories();
    }
}
