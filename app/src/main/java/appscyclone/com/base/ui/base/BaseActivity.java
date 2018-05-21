package appscyclone.com.base.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import appscyclone.com.base.R;
import appscyclone.com.base.data.local.CustomTransaction;
import appscyclone.com.base.others.dialog.LoadingDialog;
import appscyclone.com.base.utils.NetworkUtils;
import butterknife.Unbinder;

/*
 * Created by NhatHoang on 14/05/2018.
 */
public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    private LoadingDialog mDialogView;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    @Override
    public void showLoading() {
        if (NetworkUtils.isNetworkConnected(this))
            if (mDialogView != null) {
                mDialogView.show();
            } else {
                mDialogView = new LoadingDialog(this);
                mDialogView.setCanceledOnTouchOutside(false);
                mDialogView.show();
            }
    }

    @Override
    public void hideLoading() {
        if (mDialogView != null) {
            mDialogView.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public void addFragment(BaseFragment fragment, boolean isAddToBackStack, boolean isAnimation) {
        addReplaceFragment(new CustomTransaction(isAnimation), fragment, false, isAddToBackStack);
    }

    @Override
    public void replaceFragment(BaseFragment fragment, boolean isAddToBackStack, boolean isAnimation) {
        addReplaceFragment(new CustomTransaction(isAnimation), fragment, true, isAddToBackStack);
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    private void addReplaceFragment(CustomTransaction customTransaction, BaseFragment fragment, boolean isReplace, boolean isAddToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager != null && fragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (customTransaction.isAnimation)
                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            if (isReplace)
                fragmentTransaction.replace(customTransaction.containerViewId != 0 ? customTransaction.containerViewId : android.R.id.tabcontent, fragment);
            else {
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(customTransaction.containerViewId != 0 ? customTransaction.containerViewId : android.R.id.tabcontent);
                if (currentFragment != null) {
                    fragmentTransaction.hide(currentFragment);
                }
                fragmentTransaction.add(customTransaction.containerViewId != 0 ? customTransaction.containerViewId : android.R.id.tabcontent, fragment, fragment.getClass().getSimpleName());
            }
            if (isAddToBackStack) {
                fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
            }
            fragmentTransaction.commit();
        }
    }

    public void clearAllBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        int count = fm.getBackStackEntryCount();
        for (int i = 0; i < count; ++i) {
            fm.popBackStack();
        }
    }

}
