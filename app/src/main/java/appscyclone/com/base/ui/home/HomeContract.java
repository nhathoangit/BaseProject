package appscyclone.com.base.ui.home;

import java.util.List;

import appscyclone.com.base.data.network.model.ItemModel;
import appscyclone.com.base.data.network.model.ResItemsModel;
import appscyclone.com.base.ui.base.MvpView;

/*
 * Created by NhatHoang on 07/09/2018.
 */
public interface HomeContract {
    interface HomeView extends MvpView {
        void loadListItems(List<ItemModel> data, int total);

        void updateListItems(List<ItemModel> data, ResItemsModel responseData);

        void loadDataError(String errorMessage);
    }

    interface HomePresenter {

        void getListItems(int page);

    }
}
