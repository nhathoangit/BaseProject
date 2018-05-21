package appscyclone.com.base.ui.base;

import android.support.v4.app.Fragment;

import butterknife.Unbinder;

/*
 * Created by NhatHoang on 14/05/2018.
 */
public abstract class BaseFragment extends Fragment implements MvpView {

    private Unbinder mUnBinder;

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

    @Override
    public void addFragment(BaseFragment fragment, boolean isAddToBackStack, boolean isAnimation) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).addFragment(fragment, false, isAnimation);
        }
    }

    @Override
    public void replaceFragment(BaseFragment fragment, boolean isAddToBackStack, boolean isAnimation) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).replaceFragment(fragment, isAddToBackStack, isAnimation);
        }
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    public void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }
}
