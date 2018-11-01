package appscyclone.com.base.ui.multipleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
 * Created by NhatHoang on 31/10/2018.
 */
public class MutipleTypeAdapter extends ACBaseAdapter<ACRecyclerView.ACBaseViewHolder> {
    private static final int TEXT = 1;
    private static final int IMAGE = 2;
    private List<ItemModel> data;

    protected MutipleTypeAdapter(List<ItemModel> data) {
        super(data);
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position) != null) {
            if (data.get(position).isHomeFeed() == TEXT)
                return TEXT;
            else if (data.get(position).isHomeFeed() == IMAGE)
                return IMAGE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public ACRecyclerView.ACBaseViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TEXT:
                View itemView0 = li.inflate(R.layout.item_text, parent, false);
                return new TextViewHolder(itemView0);
            case IMAGE:
                View itemView1 = li.inflate(R.layout.item_image, parent, false);
                return new ImageViewHolder(itemView1);
            default:
                break;
        }
        return null;
    }

    static class ImageViewHolder extends ACRecyclerView.ACBaseViewHolder<ItemModel> {
        @BindView(R.id.itemImage_ivImage)
        ImageView ivImage;

        ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindData(ItemModel data) {
            Glide.with(itemView).load(data.getSlideModel().getUrl()).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(ivImage);
        }
    }

    class TextViewHolder extends ACRecyclerView.ACBaseViewHolder<ItemModel> {

        @BindView(R.id.itemTest_tvText)
        TextView tvText;

        TextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindData(ItemModel data) {
            tvText.setText(data.getTitle());
        }
    }
}
