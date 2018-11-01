package appscyclone.com.base.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appscyclone.aclibrary.view.ACRecyclerView;

import java.util.List;

import javax.inject.Inject;

import appscyclone.com.base.R;
import appscyclone.com.base.data.network.model.ItemModel;
import appscyclone.com.base.data.network.model.ResItemsModel;
import appscyclone.com.base.ui.base.BaseFragment;
import appscyclone.com.base.ui.multipleviewtype.MultipleTypeFragment;
import appscyclone.com.base.utils.AppUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 * Created by NhatHoang on 06/09/2018.
 */
public class HomeFragment extends BaseFragment implements HomeContract.HomeView, ACRecyclerView.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    HomePresenterImp presenterImp;

    @BindView(R.id.fragHome_rvData)
    ACRecyclerView rvData;

    private GridLayoutManager gridLayoutManager;
    private HomeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setUnBinder(ButterKnife.bind(this, view));
        getActivityComponent().inject(this);
        presenterImp.onAttach(this);
        initialize();
        return view;
    }

    private void initialize() {
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvData.setLayoutManager(gridLayoutManager);
        rvData.setHasFixedSize(true);
        rvData.setLoadMore(this);
        rvData.setRefresh(this);
        showLoading();
        presenterImp.getListItems(rvData.getPage());
    }

    @Override
    public void loadListItems(List<ItemModel> data, int total) {
        adapter = new HomeAdapter(data);
        rvData.setAdapter(adapter);
        rvData.setTotalItemsSize(total);
    }

    @Override
    public void updateListItems(List<ItemModel> data,  ResItemsModel responseData) {
        rvData.handleDataResponseByTotalItems(responseData.getTotal());
        data.addAll(responseData.items);
        rvData.notifyItemRangeInserted(rvData.getAdapter().getItemCount(), data.size());
    }

    @Override
    public void loadDataError(String errorMessage) {
        AppUtils.showDialogMessage(getActivity(), "Message", errorMessage, null);
    }

    @Override
    public void onLoadMore() {
        presenterImp.getListItems(rvData.getPage());
    }

    @Override
    public void onRefresh() {
        presenterImp.getListItems(rvData.getPage());
    }

    @Override
    public void onDestroy() {
        presenterImp.onDetach();
        super.onDestroy();
    }

    @OnClick(R.id.fragHome_btnClick)
    public void onViewClicked() {
        addFragment(new MultipleTypeFragment(),true,false);
    }

}
