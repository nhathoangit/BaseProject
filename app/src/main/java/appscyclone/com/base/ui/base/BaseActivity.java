package appscyclone.com.base.ui.base;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import appscyclone.com.base.R;
import appscyclone.com.base.data.local.CustomTransaction;
import appscyclone.com.base.di.component.ActivityComponent;
import appscyclone.com.base.others.dialog.LoadingDialog;
import appscyclone.com.base.ui.main.MainActivity;
import appscyclone.com.base.utils.NetworkUtils;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


/*
 * Created by NhatHoang on 14/05/2018.
 */
public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    public ActivityComponent mActivityComponent;
    private LoadingDialog mDialogView;
    private Unbinder mUnBinder;

    protected abstract int getLayoutId();

    protected abstract void initView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = BaseApplication.get(this).getComponent().inject();
        setContentView(getLayoutId());
        setUnBinder(ButterKnife.bind(this));
        initView();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
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
                fragmentTransaction.replace(customTransaction.containerViewId != 0 ? customTransaction.containerViewId : this instanceof MainActivity ? R.id.frmContainer : R.id.frmContainer, fragment);
            else {
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(customTransaction.containerViewId != 0 ? customTransaction.containerViewId : this instanceof MainActivity ? R.id.frmContainer : R.id.frmContainer);
                if (currentFragment != null) {
                    fragmentTransaction.hide(currentFragment);
                }
                fragmentTransaction.add(customTransaction.containerViewId != 0 ? customTransaction.containerViewId : this instanceof MainActivity ? R.id.frmContainer : R.id.frmContainer, fragment, fragment.getClass().getSimpleName());
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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
