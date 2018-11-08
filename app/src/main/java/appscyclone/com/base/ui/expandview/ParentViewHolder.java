package appscyclone.com.base.ui.expandview;
/*
 * Created by HoangDong on 20/09/2017.
 */

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.appscyclone.aclibrary.view.ACRecyclerView;

import appscyclone.com.base.R;


public class ParentViewHolder extends ACRecyclerView.ACParentViewHolder {
    private ImageView imageView;
    public ParentViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, R.layout.item_expand_parent);
        imageView=itemView.findViewById(R.id.list_item_genre_arrow);
    }

    @Override
    public void bindData(Object data) {
    }

    @Override
    public View getViewRotate() {
        return imageView;
    }


}
