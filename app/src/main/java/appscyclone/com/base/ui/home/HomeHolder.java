package appscyclone.com.base.ui.home;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appscyclone.aclibrary.view.ACRecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import appscyclone.com.base.R;
import appscyclone.com.base.data.network.model.ItemModel;
import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * Created by NhatHoang on 07/09/2018.
 */
public class HomeHolder extends ACRecyclerView.ACBaseViewHolder<ItemModel> {
    @BindView(R.id.item_grid_ivTest)
    ImageView ivTest;

    public HomeHolder(ViewGroup parent, int res) {
        super(parent, R.layout.item_grid_test);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindData(ItemModel data) {
        Glide.with(itemView).load(data.getSlideModel().getUrl()).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(ivTest);
    }
}
