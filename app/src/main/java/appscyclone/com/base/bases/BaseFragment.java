package appscyclone.com.base.bases;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import appscyclone.com.base.R;
import appscyclone.com.base.interfaces.FragmentResultListener;

/*
 * Created by NhatHoang on 20/04/2018.
 */
public abstract class BaseFragment extends Fragment {

    private FragmentResultListener resultListener;
    private int mCodeRequest;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (getContext() != null)
            view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (((BaseApplication) getActivity().getApplication()).getCancelAnimation()) {
            nextAnim = enter ? android.R.anim.fade_in : android.R.anim.fade_out;
        }
        Animation animation = super.onCreateAnimation(transit, enter, nextAnim);
        if (animation == null && nextAnim != 0) {
            animation = AnimationUtils.loadAnimation(getActivity(), nextAnim);
        }
        if (animation != null && getView() != null) {
            getView().setLayerType(View.LAYER_TYPE_HARDWARE, null);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    if (getView() != null)
                        getView().setClickable(true);
                }

                public void onAnimationEnd(Animation animation) {
                    if (getView() != null)
                        getView().setClickable(false);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

            });
        }
        return animation;
    }

    public FragmentResultListener getResultListener() {
        return resultListener;
    }

    public void setFragmentForResult(int codeRequest, FragmentResultListener fragmentResultListener) {
        this.resultListener = fragmentResultListener;
        this.mCodeRequest = codeRequest;
    }

    protected void callBackFragmentResult(Bundle bundle) {
        if (resultListener != null)
            resultListener.onFragmentForResult(mCodeRequest, bundle);
    }

    protected void callBackFragmentResult(int code) {
        if (resultListener != null)
            resultListener.onFragmentForResult(code, new Bundle());
    }

    public void clearAllBackStack() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).clearAllBackStack();
        }
    }

    public void replaceFragment(BaseFragment fragment, boolean isAddToBackStack, boolean isAnimation) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).replaceFragment(fragment, isAddToBackStack, isAnimation);
        }
    }

    public void addFragment(BaseFragment fragment, boolean isAddToBackStack, boolean isAnimation) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).addFragment(fragment, isAddToBackStack, isAnimation);
        }
    }

}
