package appscyclone.com.base.ui.headerfooter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appscyclone.aclibrary.view.ACRecyclerView;
import com.appscyclone.aclibrary.view.adapter.ACHFBaseAdapter;
import com.bumptech.glide.Glide;

import java.util.List;

import appscyclone.com.base.R;
import appscyclone.com.base.data.network.model.ItemModel;
import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * Created by NhatHoang on 02/11/2018.
 */
public class HFAdapter extends ACHFBaseAdapter<ACRecyclerView.ACBaseViewHolder> {
    List<ItemModel> data;

    public HFAdapter(List<ItemModel> data, boolean withHeader, boolean withFooter) {
        super(data, withHeader, withFooter);
        this.data = data;
    }

    @Override
    public ACRecyclerView.ACBaseViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new HFAdapter.ItemViewHolder(view);
    }

    @Override
    protected ACRecyclerView.ACBaseViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
        return new HFAdapter.HeaderViewHolder(view);
    }

    @Override
    protected ACRecyclerView.ACBaseViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footer, parent, false);
        return new HFAdapter.FooterViewHolder(view);
    }

    class ItemViewHolder extends ACRecyclerView.ACBaseViewHolder<ItemModel> {
        @BindView(R.id.itemImage_ivImage)
        ImageView ivImage;

        @BindView(R.id.itemImage_tvText)
        TextView tvText;

        ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindData(ItemModel data) {
            tvText.setText(data.getTitle());
            Glide.with(itemView).load(data.getSlideModel().getUrl()).into(ivImage);
        }

    }

    class HeaderViewHolder extends ACRecyclerView.ACBaseViewHolder  {
        HeaderViewHolder(View view) {
            super(view);
        }
    }

    class FooterViewHolder extends ACRecyclerView.ACBaseViewHolder {
        FooterViewHolder(View view) {
            super(view);
        }
    }

}
