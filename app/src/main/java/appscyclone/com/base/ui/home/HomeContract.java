package appscyclone.com.base.ui.home;

import appscyclone.com.base.data.network.model.ItemModel;
import appscyclone.com.base.data.network.model.ResItemsModel;
import appscyclone.com.base.ui.base.MvpView;

/*
 * Created by NhatHoang on 07/09/2018.
 */
public interface HomeContract {
    interface HomeView extends MvpView {
        void loadListItems(ResItemsModel itemModel);
    }

    interface HomePresenter {
        void getListItems(int page);
    }
}
