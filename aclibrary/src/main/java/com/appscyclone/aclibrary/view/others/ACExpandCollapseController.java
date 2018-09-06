package com.appscyclone.aclibrary.view.others;
/*
 * Created by HoangDong on 20/09/2017.
 */

public class ACExpandCollapseController {
    private ACExpandCollapseListener listener;
    private ACExpandGroupList expandableList;

    public ACExpandCollapseController(ACExpandGroupList expandableList, ACExpandCollapseListener listener) {
        this.expandableList = expandableList;
        this.listener = listener;
    }

    public boolean toggleGroup(int flatPos) {
        ACExpandListPosition listPos = expandableList.getUnflattenedPosition(flatPos);
        boolean expanded = expandableList.expandedGroupIndexes[listPos.groupPos];
        if (expanded) {
            collapseGroup(listPos);
        } else {
            expandGroup(listPos);
        }
        return expanded;
    }

    private void collapseGroup(ACExpandListPosition listPosition) {
        expandableList.expandedGroupIndexes[listPosition.groupPos] = false;
        if (listener != null) {
            listener.onGroupCollapsed(expandableList.getFlattenedGroupIndex(listPosition) + 1,
                    expandableList.groups.get(listPosition.groupPos).getItemCount());
        }
    }

    private void expandGroup(ACExpandListPosition listPosition) {
        expandableList.expandedGroupIndexes[listPosition.groupPos] = true;
        if (listener != null) {
            listener.onGroupExpanded(expandableList.getFlattenedGroupIndex(listPosition) + 1,
                    expandableList.groups.get(listPosition.groupPos).getItemCount());
        }
    }
}
