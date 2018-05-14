package appscyclone.com.base.ui.base;

/*
 * Created by NhatHoang on 14/05/2018.
 */
public interface IPresenter <V extends MvpView> {
    void onAttach(V mvpView);

    void onDetach();

    boolean isViewAttach();
}
