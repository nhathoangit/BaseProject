package appscyclone.com.base.ui.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appscyclone.aclibrary.view.ACRecyclerView;
import com.appscyclone.aclibrary.view.adapter.ACBaseAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import appscyclone.com.base.R;
import appscyclone.com.base.data.network.model.ItemModel;
import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * Created by NhatHoang on 16/10/2018.
 */
public class HomeAdapter extends ACBaseAdapter<ACRecyclerView.ACBaseViewHolder> {
    List<ItemModel> data;

    public HomeAdapter(List<ItemModel> data) {
        super(data);
        this.data = data;
    }

    @Override
    public ACRecyclerView.ACBaseViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_test, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    static class HomeViewHolder extends ACRecyclerView.ACBaseViewHolder<ItemModel> {
        @BindView(R.id.item_grid_ivTest)
        ImageView ivTest;

        HomeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindData(ItemModel data) {
            Glide.with(itemView).load(data.getSlideModel().getUrl()).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(ivTest);
        }
    }
}
