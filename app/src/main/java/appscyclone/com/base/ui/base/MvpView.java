package appscyclone.com.base.ui.base;

/*
 * Created by NhatHoang on 14/05/2018.
 */
public interface MvpView {
    void showLoading();

    void hideLoading();

    boolean isNetworkConnected();

    void addFragment(BaseFragment fragment, boolean isAddToBackStack, boolean isAnimation);

    void replaceFragment(BaseFragment fragment, boolean isAddToBackStack, boolean isAnimation);

}
