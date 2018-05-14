package appscyclone.com.base.ui.base;

import android.support.v4.app.Fragment;

/*
 * Created by NhatHoang on 14/05/2018.
 */
public abstract class BaseFragment extends Fragment implements MvpView {

    @Override
    public void showLoading() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).hideLoading();
        }
    }

    @Override
    public boolean isNetworkConnected() {
        return getActivity() instanceof BaseActivity && ((BaseActivity) getActivity()).isNetworkConnected();
    }
}
