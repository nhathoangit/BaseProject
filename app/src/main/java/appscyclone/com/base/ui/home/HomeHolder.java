package appscyclone.com.base.ui.home;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appscyclone.aclibrary.view.ACRecyclerView;

import appscyclone.com.base.R;
import appscyclone.com.base.data.network.model.ItemModel;
import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * Created by NhatHoang on 07/09/2018.
 */
public class HomeHolder extends ACRecyclerView.ACBaseViewHolder<ItemModel> {
    @BindView(R.id.itemTest_imgImageView)
    ImageView imgImageView;
    @BindView(R.id.itemTest_tvTitle)
    TextView tvTitle;

    public HomeHolder(ViewGroup parent, int res) {
        super(parent, R.layout.item_test);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindData(ItemModel data) {
        tvTitle.setText(data.getTitle());
    }
}
