package appscyclone.com.base.ui.multipleview;

import java.util.List;

import appscyclone.com.base.data.network.model.ItemModel;
import appscyclone.com.base.data.network.model.ResItemsModel;
import appscyclone.com.base.ui.base.MvpView;

/*
 * Created by NhatHoang on 31/10/2018.
 */
public interface MultipleTypeContract {
    interface MultipleTypeView extends MvpView {
        void loadListItems(List<ItemModel> data, int total);

        void updateListItems(List<ItemModel> data, ResItemsModel responseData);

        void loadDataError(String errorMessage);
    }

    interface MultipleTypePresenter {
        void getListItems(int page);

    }
}
