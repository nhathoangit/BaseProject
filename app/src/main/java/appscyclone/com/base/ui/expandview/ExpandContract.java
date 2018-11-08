package appscyclone.com.base.ui.expandview;

import java.util.List;

import appscyclone.com.base.data.network.model.ItemModel;
import appscyclone.com.base.ui.base.MvpView;

/*
 * Created by NhatHoang on 06/11/2018.
 */
public interface ExpandContract {
    interface ExpandView extends MvpView {
        void loadListItems(List<ItemModel> data, int total);
    }

    interface ExpandPresenter {
        void getListItems();
    }
}
