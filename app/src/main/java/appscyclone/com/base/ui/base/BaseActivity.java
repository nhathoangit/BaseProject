package appscyclone.com.base.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import appscyclone.com.base.others.dialog.LoadingDialog;
import appscyclone.com.base.utils.NetworkUtils;

/*
 * Created by NhatHoang on 14/05/2018.
 */
public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    private LoadingDialog mDialogView;

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

}
