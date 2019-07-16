package appscyclone.com.base.ui.headerfooter;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appscyclone.aclibrary.view.ACRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import appscyclone.com.base.R;
import appscyclone.com.base.data.network.model.ItemModel;
import appscyclone.com.base.data.network.model.ResItemsModel;
import appscyclone.com.base.ui.base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 * Created by NhatHoang on 02/11/2018.
 */
public class HFFragment extends BaseFragment implements ACRecyclerView.OnLoadMoreListener, HFContract.HFView {

    @Inject
    HFPresenterImp presenterImp;

    @BindView(R.id.fragMultipleType_rvData)
    ACRecyclerView rvData;

    private HFAdapter adapter;
    private List<ItemModel> models;

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
        rvData.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        models = new ArrayList<>();
        //rvData.setLayoutManager(new CustomLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvData.setHasFixedSize(true);
        //rvData.setLoadMore(this);
        showLoading();
        presenterImp.getListItems(rvData.getPage());
    }


    @Override
    public void onLoadMore() {
        presenterImp.getListItems(rvData.getPage());
    }

    @Override
    public void loadListItems(List<ItemModel> data, int total) {
        adapter = new HFAdapter(data, true, true);
        rvData.setAdapter(adapter);
        rvData.setTotalItemsSize(total);
        models.addAll(data);
    }

    @Override
    public void updateListItems(List<ItemModel> data, ResItemsModel responseData) {
        rvData.handleDataResponseByTotalItems(responseData.getTotal());
        data.addAll(responseData.items);
        models.addAll(responseData.items);
        rvData.notifyItemRangeInserted(rvData.getAdapter().getItemCount(), data.size());

    }

    @Override
    public void loadDataError(String errorMessage) {

    }

    @OnClick(R.id.fragHome_btnClick)
    public void onViewClicked() {
        Toast.makeText(getContext(), models.size() + "", Toast.LENGTH_SHORT).show();
    }
}
