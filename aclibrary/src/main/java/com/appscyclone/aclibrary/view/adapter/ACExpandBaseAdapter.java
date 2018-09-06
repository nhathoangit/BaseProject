package com.appscyclone.aclibrary.view.adapter;
/*
 * Created by HoangDong on 19/09/2017.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ArrayRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.appscyclone.aclibrary.view.ACRecyclerView;
import com.appscyclone.aclibrary.view.model.ACBaseGroupModel;
import com.appscyclone.aclibrary.view.others.ACExpandCollapseController;
import com.appscyclone.aclibrary.view.others.ACExpandCollapseListener;
import com.appscyclone.aclibrary.view.others.ACExpandGroupList;
import com.appscyclone.aclibrary.view.others.ACExpandListPosition;

import java.util.List;

public abstract class ACExpandBaseAdapter<GVH extends ACRecyclerView.ACParentViewHolder, CVH extends ACRecyclerView.ACChildViewHolder>
        extends RecyclerView.Adapter implements ACExpandCollapseListener, ACRecyclerView.OnGroupClickListener {


    protected ACExpandGroupList expandableList;
    private ACExpandCollapseController expandCollapseController;

    private ACRecyclerView.OnGroupClickListener groupClickListener;
    private ACRecyclerView.GroupExpandCollapseListener expandCollapseListener;

    private Integer[] mArrIdRes;

    public void setParentClick(boolean parentClick) {
        isParentClick = parentClick;
    }

    private boolean isParentClick;
    private ACRecyclerView.OnItemExpandListener onItemListener;


    public void setOnItemExpandListener(ACRecyclerView.OnItemExpandListener itemListener, @ArrayRes Integer[] arrIdRes) {
        this.onItemListener = itemListener;
        this.mArrIdRes = arrIdRes;
    }

    public ACExpandBaseAdapter(List<? extends ACBaseGroupModel> groups,boolean isExpandAll) {
        this.expandableList = new ACExpandGroupList(groups,isExpandAll);
        this.expandCollapseController = new ACExpandCollapseController(expandableList, this);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ACExpandListPosition.GROUP:
                GVH gvh = onCreateGroupViewHolder(parent, viewType);
                gvh.handleItemListener(gvh.itemView);
                gvh.setOnGroupClickListener(this);
                handleOnClick(gvh);
                return gvh;
            case ACExpandListPosition.CHILD:
                CVH cvh = onCreateChildViewHolder(parent, viewType);
                handleOnClick(cvh);
                return cvh;
            default:
                throw new IllegalArgumentException("viewType is not valid");
        }
    }

    private void handleOnClick(RecyclerView.ViewHolder vh)
    {
        if (mArrIdRes != null && onItemListener != null) {
            View view;
            for (Integer mArrIdRe : mArrIdRes) {
                view = vh.itemView.findViewById(mArrIdRe);
                if (view != null)
                    view.setOnClickListener(view1 -> {
                        ACExpandListPosition listPos = expandableList.getUnflattenedPosition(vh.getAdapterPosition());
                        onItemListener.onItemExpandClicked(view1,listPos.groupPos, listPos.childPos);
                    });
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ACExpandListPosition listPos = expandableList.getUnflattenedPosition(position);
        ACBaseGroupModel group = expandableList.getExpandableGroup(listPos);
        switch (listPos.type) {
            case ACExpandListPosition.GROUP:
                onBindGroupViewHolder((GVH) holder, position, group);
                break;
            case ACExpandListPosition.CHILD:
                onBindChildViewHolder((CVH) holder, position, group, listPos.childPos);
                break;
        }

    }
    @Override
    public void onGroupExpanded(int positionStart, int itemCount) {
        if (itemCount > 0) {
            notifyItemRangeInserted(positionStart, itemCount);
            if (expandCollapseListener != null) {
                int groupIndex = expandableList.getUnflattenedPosition(positionStart).groupPos;
                expandCollapseListener.onGroupExpanded(getGroups().get(groupIndex));
            }
        }

    }

    @Override
    public void onGroupCollapsed(int positionStart, int itemCount) {
        if (itemCount > 0) {
            notifyItemRangeRemoved(positionStart, itemCount);
            if (expandCollapseListener != null) {
                //minus one to return the position of the header, not first child
                int groupIndex = expandableList.getUnflattenedPosition(positionStart - 1).groupPos;
                expandCollapseListener.onGroupCollapsed(getGroups().get(groupIndex));
            }
        }
    }

    public List<? extends ACBaseGroupModel> getGroups() {
        return expandableList.groups;
    }

    @Override
    public int getItemCount() {
        return expandableList.getVisibleItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return expandableList.getUnflattenedPosition(position).type;
    }

    @Override
    public boolean onGroupClick(int GroupPos) {
        if((GroupPos+1)==expandableList.groups.size())
            notifyDataSetChanged();
        if (groupClickListener != null) {
            groupClickListener.onGroupClick(GroupPos);
        }
        if (onItemListener != null && isParentClick) {
            onItemListener.onItemExpandClicked(null, GroupPos,-1);
        }
        return expandCollapseController.toggleGroup(GroupPos);
    }

    public void setOnGroupClickListener(ACRecyclerView.OnGroupClickListener listener) {
        groupClickListener = listener;
    }

    public abstract GVH onCreateGroupViewHolder(ViewGroup parent, int viewType);

    public abstract CVH onCreateChildViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindChildViewHolder(CVH holder, int flatPosition, ACBaseGroupModel group,
                                               int childIndex);

    public abstract void onBindGroupViewHolder(GVH holder, int flatPosition, ACBaseGroupModel group);

}
