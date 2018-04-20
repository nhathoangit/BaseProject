package appscyclone.com.base.bases;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import appscyclone.com.base.R;
import appscyclone.com.base.models.CustomTransaction;

/*
 * Created by NhatHoang on 20/04/2018.
 */
public abstract class BaseActivity extends AppCompatActivity {

    View.OnClickListener onBackClick = view -> onBackPressed();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setActionBar(View view, String title) {
        TextView tvTitle = view.findViewById(R.id.actionbar_tvTitle);
        View vBack = view.findViewById(R.id.actionbar_imgBack);
        if (tvTitle != null) {
            tvTitle.setText(title);
        }
        if (vBack != null) {
            vBack.setOnClickListener(onBackClick);
        }
    }

    private void addReplaceFragment(CustomTransaction customTransaction, BaseFragment fragment, boolean isReplace, boolean isAddToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager != null && fragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (customTransaction.isAnimation)
                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            if (isReplace)
                fragmentTransaction.replace(customTransaction.containerViewId != 0 ? customTransaction.containerViewId : R.id.frmContainer, fragment);
            else {
                android.support.v4.app.Fragment currentFragment = getSupportFragmentManager().findFragmentById(customTransaction.containerViewId != 0 ? customTransaction.containerViewId : R.id.frmContainer);
                if (currentFragment != null) {
                    fragmentTransaction.hide(currentFragment);
                }
                fragmentTransaction.add(customTransaction.containerViewId != 0 ? customTransaction.containerViewId : R.id.frmContainer, fragment, fragment.getClass().getSimpleName());
            }
            if (isAddToBackStack) {
                fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
            }
            fragmentTransaction.commit();
        }
    }

    public void addFragment(BaseFragment fragment, boolean isAddToBackStack, boolean isAnimation) {
        addReplaceFragment(new CustomTransaction(isAnimation), fragment, false, isAddToBackStack);
    }

    public void replaceFragment(BaseFragment fragment, boolean isAddToBackStack, boolean isAnimation) {
        addReplaceFragment(new CustomTransaction(isAnimation), fragment, true, isAddToBackStack);
    }

    public void clearAllBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        int count = fm.getBackStackEntryCount();
        for (int i = 0; i < count; ++i) {
            fm.popBackStack();
        }
    }


}
