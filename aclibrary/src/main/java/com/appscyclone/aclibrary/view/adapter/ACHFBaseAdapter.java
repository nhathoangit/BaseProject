package com.appscyclone.aclibrary.view.adapter;

import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.appscyclone.aclibrary.R;
import com.appscyclone.aclibrary.view.ACRecyclerView;

import java.util.List;

/*
 * Created by NhatHoang on 02/11/2018.
 */
abstract public class ACHFBaseAdapter<VH extends ACRecyclerView.ACBaseViewHolder> extends ACBaseAdapter<VH> {
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_LOADING = -1;
    public static final int TYPE_HEADER = -2;
    public static final int TYPE_FOOTER = -3;

    private View viewLoadMore;
    private List<?> mData;
    private boolean isLoading;
    private boolean withHeader;
    private boolean withFooter;
    private boolean isParentClick;
    private int mColorLoadMore = -1;

    private ACRecyclerView.OnItemListener onItemListener;

    public ACHFBaseAdapter(List<?> data, boolean withHeader, boolean withFooter) {
        super(data);
        this.mData = data;
        this.withHeader = withHeader;
        this.withFooter = withFooter;
    }

    public void setParentClick(boolean parentClick) {
        isParentClick = parentClick;
    }

    public void setOnItemListener(ACRecyclerView.OnItemListener itemListener) {
        this.onItemListener = itemListener;
    }

    public void setViewLoadMore(View viewLoadMore) {
        this.viewLoadMore = viewLoadMore;
    }

    public void setColorLoadMore(@ColorRes int color) {
        this.mColorLoadMore = color;
    }

    @Override
    public int getItemCount() {
        int itemCount = getRealItemCount();
        if (withHeader) itemCount++;
        if (withFooter) itemCount++;
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (withHeader && isPositionHeader(position)) return TYPE_HEADER;
        if (withFooter) {
            if (isPositionFooter(position)) {
                return TYPE_FOOTER;
            }
        } else {
            if (mData.get(position - 1) == null)
                return TYPE_LOADING;
        }
        return TYPE_ITEM;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ITEM:
                VH vh = (VH) onCreateBaseViewHolder(parent, viewType);
                vh.onCreatedView(vh.itemView);
                if (onItemListener != null && isParentClick) {
                    vh.itemView.setOnClickListener(view -> {
                        onItemListener.onItemClicked(view, vh.getAdapterPosition());
                    });

                    int[] attrs = new int[]{android.R.attr.selectableItemBackground};
                    TypedArray a = vh.itemView.getContext().getTheme().obtainStyledAttributes(attrs);
                    Drawable d = a.getDrawable(0);
                    a.recycle();
                    if (vh.itemView instanceof FrameLayout && ((FrameLayout) vh.itemView).getForeground() == null) {
                        FrameLayout frameLayout = (FrameLayout) vh.itemView;
                        frameLayout.setForeground(d);
                    }

                }
                return vh;
            case TYPE_LOADING:
                viewLoadMore = LayoutInflater.from(parent.getContext()).inflate(R.layout.acview_loading, parent, false);
                if (viewLoadMore.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
                    ((StaggeredGridLayoutManager.LayoutParams) viewLoadMore.getLayoutParams()).setFullSpan(true);
                }
                return (VH) new ACHFBaseAdapter.LoadingViewHolder(viewLoadMore);
            case TYPE_HEADER:
                VH hvh = (VH) onCreateHeaderViewHolder(parent, viewType);
                hvh.onCreatedView(hvh.itemView);
                return hvh;
            case TYPE_FOOTER:
                VH fvh = (VH) onCreateFooterViewHolder(parent, viewType);
                fvh.onCreatedView(fvh.itemView);
                return fvh;
            default:
                return (VH) onCreateBaseViewHolder(parent, viewType);
        }

    }

    protected abstract ACRecyclerView.ACBaseViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType);

    protected abstract ACRecyclerView.ACBaseViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        if (!(withHeader && isPositionHeader(position)) && !(withFooter && isPositionFooter(position))) {
            if ((withHeader && withFooter) || withHeader)
                position--;
            if (mData.get(position) != null)
                holder.bindData(mData.get(position));
        }
    }

    public List<?> getData() {
        return mData;
    }

    public boolean getLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    protected int getRealItemCount() {
        return mData != null ? mData.size() : 0;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private boolean isPositionFooter(int position) {
        return position == getItemCount() - 1;
    }

    private class LoadingViewHolder extends ACRecyclerView.ACBaseViewHolder {
        private ProgressBar progressBar;

        LoadingViewHolder(View view) {
            super(view);
            if (mColorLoadMore != -1) {
                progressBar = view.findViewById(R.id.acViewLoading_progressBar);
                progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(view.getContext(), mColorLoadMore), PorterDuff.Mode.SRC_IN);
            }
        }

        @Override
        public void bindData(Object data) {
        }

        @Override
        public void onCreatedView(View view) {
        }
    }
}
