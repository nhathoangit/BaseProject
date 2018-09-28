package appscyclone.com.base.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appscyclone.aclibrary.view.ACRecyclerView;
import com.appscyclone.aclibrary.view.adapter.ACBaseAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import appscyclone.com.base.R;
import appscyclone.com.base.data.network.model.ItemModel;
import appscyclone.com.base.data.network.model.ResItemsModel;
import appscyclone.com.base.ui.base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * Created by NhatHoang on 06/09/2018.
 */
public class HomeFragment extends BaseFragment implements HomeContract.HomeView, ACRecyclerView.OnLoadMoreListener {

    @Inject
    HomePresenterImp presenterImp;

    @BindView(R.id.fragHome_rvData)
    ACRecyclerView rvData;

    private List<ItemModel> mData;
    private GridLayoutManager gridLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setUnBinder(ButterKnife.bind(this, view));
        getActivityComponent().inject(this);
        presenterImp.onAttach(this);
        initialize();
        return view;
    }


    private void initialize() {
        mData = new ArrayList<>();
        gridLayoutManager = new GridLayoutManager(getActivity(),2);
        rvData.setLayoutManager(gridLayoutManager);
        rvData.setAdapter(HomeHolder.class, mData);
        rvData.setLoadMore(this);
        showLoading();
        presenterImp.getListItems(rvData.getPage());
    }

    @Override
    public void loadListItems(ResItemsModel itemModel) {
        if (rvData != null) {
            rvData.handleDataResponseByTotalItems(itemModel.getTotal());
            mData.addAll(itemModel.items);
            rvData.notifyItemRangeInserted(rvData.getAdapter().getItemCount(), mData.size());
        }
    }

    @Override
    public void onLoadMore() {
        presenterImp.getListItems(rvData.getPage());

    }

    @Override
    public void onDestroy() {
        presenterImp.onDetach();
        super.onDestroy();
    }
}
