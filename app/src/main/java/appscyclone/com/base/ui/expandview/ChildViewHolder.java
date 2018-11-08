package appscyclone.com.base.ui.expandview;
/*
 * Created by HoangDong on 20/09/2017.
 */

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;

import com.appscyclone.aclibrary.view.ACRecyclerView;

import appscyclone.com.base.R;

public class ChildViewHolder extends ACRecyclerView.ACChildViewHolder {
    public ChildViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, R.layout.item_expand_child);
    }

    @Override
    public void bindData(Object data) {

    }

}