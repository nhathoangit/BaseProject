package appscyclone.com.base.ui.main;

import appscyclone.com.base.ui.base.MvpView;

/*
 * Created by NhatHoang on 14/05/2018.
 */
public interface MainContract {
    interface MainView extends MvpView {
        void ToastMess(String mess);
    }

    interface MainPresenter {
        void ToastMess(String mess);
    }
}
