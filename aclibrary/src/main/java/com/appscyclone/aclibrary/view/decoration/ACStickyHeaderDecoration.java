package com.appscyclone.aclibrary.view.decoration;
/*
 * Created by HoangDong on 11/09/2017.
 */

import android.graphics.Canvas;
import android.graphics.Rect;
import androidx.collection.LongSparseArray;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.appscyclone.aclibrary.view.adapter.ACBaseAdapter;

public class ACStickyHeaderDecoration extends RecyclerView.ItemDecoration {

    public interface StickyHeaderAdapter<T extends RecyclerView.ViewHolder> {
        long getHeaderId(int position);

        T onCreateHeaderViewHolder(ViewGroup parent);

        void onBindHeaderViewHolder(T viewHolder, int position);
    }

    private static final long NO_HEADER_ID = -1L;
    private LongSparseArray<RecyclerView.ViewHolder> mHeaderCache;
    private StickyHeaderAdapter mAdapter;
    private boolean mRenderInline;
    private boolean mIncludeHeader = false;

    public ACStickyHeaderDecoration(StickyHeaderAdapter adapter) {
        this(adapter, false);
    }

    public ACStickyHeaderDecoration(StickyHeaderAdapter adapter, boolean renderInline) {
        mAdapter = adapter;
        mHeaderCache = new LongSparseArray<>();
        mRenderInline = renderInline;
    }

    public void setIncludeHeader(boolean mIncludeHeader) {
        this.mIncludeHeader = mIncludeHeader;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int headerHeight = 0;

        if (!mIncludeHeader) {
            if (parent.getAdapter() instanceof ACBaseAdapter) {
                int headerCount = 0;//((RecyclerArrayAdapter) parent.getAdapter()).getHeaderCount();
                int footerCount = 0;//((RecyclerArrayAdapter) parent.getAdapter()).getFooterCount();
                int dataCount = ((ACBaseAdapter) parent.getAdapter()).getItemCount();
                if (position < headerCount) {
                    return;
                }
                if (position >= headerCount + dataCount) {
                    return;
                }
                if (position >= headerCount) {
                    position -= headerCount;
                }

            }
        }

        if (position != RecyclerView.NO_POSITION
                && hasHeader(position)
                && showHeaderAboveItem(position)) {

            View header = getHeader(parent, position).itemView;
            headerHeight = getHeaderHeightForLayout(header);
        }

        outRect.set(0, headerHeight, 0, 0);
    }

    private boolean showHeaderAboveItem(int itemAdapterPosition) {
        if (itemAdapterPosition == 0) {
            return true;
        }
        return mAdapter.getHeaderId(itemAdapterPosition - 1) != mAdapter.getHeaderId(itemAdapterPosition);
    }

    public void clearHeaderCache() {
        mHeaderCache.clear();
    }

    public View findHeaderViewUnder(float x, float y) {
        for (int i = 0; i < mHeaderCache.size(); i++) {
            long key = mHeaderCache.keyAt(i);
            final View child = mHeaderCache.get(key).itemView;
            final float translationX = ViewCompat.getTranslationX(child);
            final float translationY = ViewCompat.getTranslationY(child);

            if (x >= child.getLeft() + translationX &&
                    x <= child.getRight() + translationX &&
                    y >= child.getTop() + translationY &&
                    y <= child.getBottom() + translationY) {
                return child;
            }
        }

        return null;
    }

    private boolean hasHeader(int position) {
        return mAdapter.getHeaderId(position) != NO_HEADER_ID;
    }

    private RecyclerView.ViewHolder getHeader(RecyclerView parent, int position) {
        final long key = mAdapter.getHeaderId(position);

        if (mHeaderCache.indexOfKey(key) >= 0) {
            return mHeaderCache.get(key);
        } else {
            final RecyclerView.ViewHolder holder = mAdapter.onCreateHeaderViewHolder(parent);
            final View header = holder.itemView;
            mAdapter.onBindHeaderViewHolder(holder, position);

            int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getMeasuredWidth(), View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getMeasuredHeight(), View.MeasureSpec.UNSPECIFIED);

            int childWidth = ViewGroup.getChildMeasureSpec(widthSpec,
                    parent.getPaddingLeft() + parent.getPaddingRight(), header.getLayoutParams().width);
            int childHeight = ViewGroup.getChildMeasureSpec(heightSpec,
                    parent.getPaddingTop() + parent.getPaddingBottom(), header.getLayoutParams().height);

            header.measure(childWidth, childHeight);
            header.layout(0, 0, header.getMeasuredWidth(), header.getMeasuredHeight());

            mHeaderCache.put(key, holder);
            return holder;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        if (parent.getAdapter() == null) {
            return;
        }

        final int count = parent.getChildCount();
        long previousHeaderId = -1;

        for (int layoutPos = 0; layoutPos < count; layoutPos++) {
            final View child = parent.getChildAt(layoutPos);
            int adapterPos = parent.getChildAdapterPosition(child);

            if (!mIncludeHeader) {
                if (parent.getAdapter() instanceof ACBaseAdapter) {
                    int headerCount = 0;//((RecyclerArrayAdapter) parent.getAdapter()).getHeaderCount();
                    int footerCount = 0;//((RecyclerArrayAdapter) parent.getAdapter()).getFooterCount();
                    int dataCount = ((ACBaseAdapter) parent.getAdapter()).getItemCount();
                    if (adapterPos < headerCount) {
                        continue;
                    }
                    if (adapterPos >= headerCount + dataCount) {
                        continue;
                    }
                    if (adapterPos >= headerCount) {
                        adapterPos -= headerCount;
                    }

                }
            }

            if (adapterPos != RecyclerView.NO_POSITION && hasHeader(adapterPos)) {
                long headerId = mAdapter.getHeaderId(adapterPos);

                if (headerId != previousHeaderId) {
                    previousHeaderId = headerId;
                    View header = getHeader(parent, adapterPos).itemView;
                    canvas.save();

                    final int left = child.getLeft();
                    final int top = getHeaderTop(parent, child, header, adapterPos, layoutPos);
                    canvas.translate(left, top);

                    header.setTranslationX(left);
                    header.setTranslationY(top);
                    header.draw(canvas);
                    canvas.restore();
                }
            }
        }
    }

    private int getHeaderTop(RecyclerView parent, View child, View header, int adapterPos, int layoutPos) {
        int headerHeight = getHeaderHeightForLayout(header);
        int top = ((int) child.getY()) - headerHeight;
        if (layoutPos == 0) {
            final int count = parent.getChildCount();
            final long currentId = mAdapter.getHeaderId(adapterPos);
            // find next view with header and compute the offscreen push if needed
            for (int i = 1; i < count; i++) {
                int adapterPosHere = parent.getChildAdapterPosition(parent.getChildAt(i));
                if (adapterPosHere != RecyclerView.NO_POSITION) {
                    long nextId = mAdapter.getHeaderId(adapterPosHere);
                    if (nextId != currentId) {
                        final View next = parent.getChildAt(i);
                        final int offset = ((int) next.getY()) - (headerHeight + getHeader(parent, adapterPosHere).itemView.getHeight());
                        if (offset < 0) {
                            return offset;
                        } else {
                            break;
                        }
                    }
                }
            }

            top = Math.max(0, top);
        }

        return top;
    }

    private int getHeaderHeightForLayout(View header) {
        return mRenderInline ? 0 : header.getHeight();
    }

}
