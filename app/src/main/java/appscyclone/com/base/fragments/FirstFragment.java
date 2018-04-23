package appscyclone.com.base.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import appscyclone.com.base.R;
import appscyclone.com.base.bases.BaseFragment;
import appscyclone.com.base.utils.KeyboardUtils;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/*
 * Created by NhatHoang on 20/04/2018.
 */
public class FirstFragment extends BaseFragment {
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        KeyboardUtils.setupUI(view, getActivity());
        setActionBarTitle(view, "AAAA");
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void getUser(){

    }

    @OnClick(R.id.fragFirst_btnTest)
    public void onViewClicked() {
        getUser();
    }
}
