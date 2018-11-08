package appscyclone.com.base.ui.expandview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appscyclone.aclibrary.view.ACRecyclerView;

import appscyclone.com.base.R;
import appscyclone.com.base.ui.base.BaseFragment;
import appscyclone.com.base.utils.GenerateDataUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * Created by NhatHoang on 06/11/2018.
 */
public class ExpandFragment extends BaseFragment {

    @BindView(R.id.fragMultipleType_rvData)
    ACRecyclerView rvData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiple_type, container, false);
        setUnBinder(ButterKnife.bind(this, view));
        getActivityComponent().inject(this);
        rvData.setAdapter(ParentViewHolder.class, ChildViewHolder.class,
                GenerateDataUtils.genDataExpandRecyclerView(), true);
        //presenterImp.onAttach(this);
        //initialize();
        return view;
    }

}
