package appscyclone.com.base.ui.main;

import android.widget.Toast;

import appscyclone.com.base.ui.base.BasePresenter;

/*
 * Created by NhatHoang on 14/05/2018.
 */
public class MainPresenterImp extends BasePresenter<MainContract.MainView> implements MainContract.MainPresenter{

    @Override
    public void ToastMess(String mess) {
        getView().ToastMess(mess);
    }
}
