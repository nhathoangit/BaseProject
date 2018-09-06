package com.appscyclone.aclibrary.view.decoration;
/*
 * Created by HoangDong on 12/09/2017.
 */

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.appscyclone.aclibrary.view.adapter.ACBaseAdapter;
import com.appscyclone.aclibrary.view.adapter.ACExpandBaseAdapter;

import static android.widget.LinearLayout.VERTICAL;

public class ACSpaceDecoration extends RecyclerView.ItemDecoration {

    private int mSpace, mSpaceStart, mSpaceFooter, mSpaceEdge;

    public ACSpaceDecoration(int space, int spaceStart, int spaceFooter, int spaceEdge) {
        this.mSpace = space;
        this.mSpaceStart = spaceStart;
        this.mSpaceFooter = spaceFooter;
        this.mSpaceEdge = spaceEdge;

    }

    public void setSpaceEdge(int spaceEdge) {
        this.mSpaceEdge = spaceEdge;
    }

    public void setSpaceStart(int spaceStart) {
        this.mSpaceStart = spaceStart;
    }

    public void setSpaceFooter(int spaceFooter) {
        this.mSpaceFooter = spaceFooter;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int typeExpand = 0;
        int position = parent.getChildAdapterPosition(view);
        int spanCount = 0;
        int orientation = 0;
        int spanIndex = 0;
        int headerCount = 0, footerCount = 0;
        if (parent.getAdapter() instanceof ACBaseAdapter) {
            headerCount = 0;
            footerCount = 0;
        } else if (parent.getAdapter() instanceof ACExpandBaseAdapter) {
            ACExpandBaseAdapter expandBaseAdapter = (ACExpandBaseAdapter) parent.getAdapter();
            headerCount = 0;
            footerCount = 0;
            typeExpand = expandBaseAdapter.getItemViewType(position);
        }
        if (position < 0)
            position = (int) view.getTag();

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
            spanIndex = ((StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams()).getSpanIndex();
        } else if (layoutManager instanceof GridLayoutManager) {
            orientation = ((GridLayoutManager) layoutManager).getOrientation();
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            spanIndex = ((GridLayoutManager.LayoutParams) view.getLayoutParams()).getSpanIndex();
        } else if (layoutManager instanceof LinearLayoutManager) {
            orientation = ((LinearLayoutManager) layoutManager).getOrientation();
            spanCount = 1;
            spanIndex = 0;
        }
        view.setTag(position);

        if ((position >= headerCount && position < parent.getAdapter().getItemCount() - footerCount)) {
            if (orientation == VERTICAL) {
                float expectedWidth = (float) (parent.getWidth() - mSpaceEdge * (spanCount + 1)) / spanCount;
                float originWidth = (float) parent.getWidth() / spanCount;
                float expectedX = mSpaceEdge + (expectedWidth + mSpace) * spanIndex;
                float originX = originWidth * spanIndex;
                outRect.left = (int) (expectedX - originX);
                outRect.right = (int) (originWidth - outRect.left - expectedWidth);
                if (position - headerCount < spanCount && mSpaceStart > 0) {
                    outRect.top = mSpaceStart;
                } else {
                    if (parent.getChildAdapterPosition(view) < parent
                            .getAdapter().getItemCount() - 1)
                        outRect.top = mSpace;
                    else {
                        outRect.top = mSpace;
                        outRect.bottom = mSpaceFooter;
                    }
                }
            } else {
                float expectedHeight = (float) (parent.getHeight() - mSpaceEdge * (spanCount + 1)) / spanCount;
                float originHeight = (float) parent.getHeight() / spanCount;
                float expectedY = mSpaceEdge + (expectedHeight + mSpace) * spanIndex;
                float originY = originHeight * spanIndex;
                outRect.bottom = (int) (expectedY - originY);
                outRect.top = (int) (originHeight - outRect.bottom - expectedHeight);
                if (position - headerCount < spanCount && mSpaceStart > 0) {
                    outRect.left = mSpaceStart;
                }
                if (parent.getChildAdapterPosition(view) < parent
                        .getAdapter().getItemCount() - 1)
                    outRect.right = mSpace;
                else
                    outRect.right = mSpaceFooter;
            }
        } else {
            if (orientation == VERTICAL) {
                outRect.right = outRect.left = mSpaceEdge;
                outRect.bottom = mSpaceFooter;
            } else {
                outRect.top = outRect.bottom = mSpaceEdge;
                outRect.left = mSpaceFooter;
            }
        }
        if (typeExpand == 1) {
            outRect.top = 0;

        }

    }

}
