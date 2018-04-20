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

/*
 * Created by NhatHoang on 20/04/2018.
 */
public class SecondFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        KeyboardUtils.setupUI(view, getActivity());
        setActionBarTitle(view,"BBBB");
        return view;
    }
}
