package appscyclone.com.base.ui.multipleviewtype;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appscyclone.aclibrary.view.ACRecyclerView;

import java.util.List;

import javax.inject.Inject;

import appscyclone.com.base.R;
import appscyclone.com.base.data.network.model.ItemModel;
import appscyclone.com.base.data.network.model.ResItemsModel;
import appscyclone.com.base.others.view.CustomLinearLayoutManager;
import appscyclone.com.base.ui.base.BaseFragment;
import appscyclone.com.base.utils.AppUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * Created by NhatHoang on 31/10/2018.
 */
public class MultipleTypeFragment extends BaseFragment implements MultipleTypeContract.MultipleTypeView, ACRecyclerView.OnLoadMoreListener {

    @Inject
    MultipleTypePresenterImp presenterImp;
    @BindView(R.id.fragMultipleType_rvData)
    ACRecyclerView rvData;

    private MutipleTypeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiple_type, container, false);
        setUnBinder(ButterKnife.bind(this, view));
        getActivityComponent().inject(this);
        presenterImp.onAttach(this);
        initialize();
        return view;
    }

    private void initialize() {
        rvData.setLayoutManager(new CustomLinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rvData.setHasFixedSize(true);
        rvData.setLoadMore(this);
        showLoading();
        presenterImp.getListItems(rvData.getPage());
    }

    @Override
    public void loadListItems(List<ItemModel> data, int total) {
        adapter = new MutipleTypeAdapter(data);
        rvData.setAdapter(adapter);
        rvData.setTotalItemsSize(total);
    }

    @Override
    public void updateListItems(List<ItemModel> data, ResItemsModel responseData) {
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
}
