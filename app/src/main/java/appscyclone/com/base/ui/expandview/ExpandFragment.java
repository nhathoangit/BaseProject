package appscyclone.com.base.ui.expandview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appscyclone.aclibrary.view.ACRecyclerView;

import appscyclone.com.base.R;
import appscyclone.com.base.ui.base.BaseFragment;
import appscyclone.com.base.utils.GenerateDataUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * Created by NhatHoang on 06/11/2018.
 */
public class ExpandFragment extends BaseFragment implements ACRecyclerView.OnGroupClickListener {

    @BindView(R.id.fragMultipleType_rvData)
    ACRecyclerView rvData;

    private ExpandAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiple_type, container, false);
        setUnBinder(ButterKnife.bind(this, view));
        getActivityComponent().inject(this);
        rvData.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ExpandAdapter(GenerateDataUtils.genDataExpandRecyclerView(),false);
        ((SimpleItemAnimator) rvData.getRecyclerView().getItemAnimator()).setSupportsChangeAnimations(false);
        adapter.setOnGroupClickListener(this);
        rvData.setAdapter(adapter);

        //presenterImp.onAttach(this);
        //initialize();
        return view;
    }

    @Override
    public boolean onGroupClick(int flatPos) {
        if (flatPos == adapter.getItemCount() - 1)
            rvData.post(() -> rvData.getRecyclerView().smoothScrollToPosition(adapter.getItemCount() - 1));
        return false;
    }
}
