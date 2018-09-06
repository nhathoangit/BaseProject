package com.appscyclone.aclibrary.view.adapter;
/*
 * Created by HoangDong on 08/09/2017.
 */

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.appscyclone.aclibrary.R;
import com.appscyclone.aclibrary.view.ACRecyclerView;

import java.util.List;

abstract public class ACBaseAdapter<VH extends ACRecyclerView.ACBaseViewHolder> extends RecyclerView.Adapter<VH> {

    public static final int TYPE_ITEM = 0;
    public static final int TYPE_LOADING = 1;

    private List<?> mData;
    private Integer[] mArrIdRes;
    private boolean isLoading;
    private View viewLoadMore;
    private int mColorLoadMore = -1;

    public void setParentClick(boolean parentClick) {
        isParentClick = parentClick;
    }

    private boolean isParentClick;
    private ACRecyclerView.OnItemListener onItemListener;

    public void setOnItemListener(ACRecyclerView.OnItemListener itemListener) {
        this.onItemListener = itemListener;
    }

    public void setOnItemListener(ACRecyclerView.OnItemListener itemListener, @ArrayRes Integer[] arrIdRes) {
        this.onItemListener = itemListener;
        this.mArrIdRes = arrIdRes;
    }

    public void setViewLoadMore(View viewLoadMore) {
        this.viewLoadMore = viewLoadMore;
    }

    public void setColorLoadMore(@ColorRes int color) {
        this.mColorLoadMore = color;
    }

    protected ACBaseAdapter(List<?> data) {
        this.mData = data;
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position) == null ? TYPE_LOADING : TYPE_ITEM;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
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
                if (mArrIdRes != null && onItemListener != null) {
                    View view;
                    for (Integer mArrIdRe : mArrIdRes) {
                        view = vh.itemView.findViewById(mArrIdRe);
                        if (view != null)
                            view.setOnClickListener(view1 -> onItemListener.onItemClicked(view1, vh.getAdapterPosition()));
                    }
                }
                return vh;
            case TYPE_LOADING:
                if (viewLoadMore == null)
                    viewLoadMore = LayoutInflater.from(parent.getContext()).inflate(R.layout.acview_loading, parent, false);
                if(viewLoadMore.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams)
                {
                    ((StaggeredGridLayoutManager.LayoutParams) viewLoadMore.getLayoutParams()).setFullSpan(true);
                }
                return (VH) new LoadingViewHolder(viewLoadMore);
            default:
                return (VH) onCreateBaseViewHolder(parent, viewType);
        }

    }

    abstract public ACRecyclerView.ACBaseViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bindData(mData.get(position));
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

    public List<?> getData() {
        return mData;
    }

    public boolean getLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }
}
