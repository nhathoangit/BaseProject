package appscyclone.com.base.ui.headerfooter;

import java.util.List;

import appscyclone.com.base.data.network.model.ItemModel;
import appscyclone.com.base.data.network.model.ResItemsModel;
import appscyclone.com.base.ui.base.MvpView;

/*
 * Created by NhatHoang on 02/11/2018.
 */
public interface HFContract {
    interface HFView extends MvpView {
        void loadListItems(List<ItemModel> data, int total);

        void updateListItems(List<ItemModel> data, ResItemsModel responseData);

        void loadDataError(String errorMessage);
    }

    interface HFPresenter {

        void getListItems(int page);

    }
}
