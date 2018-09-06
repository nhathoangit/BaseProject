package com.appscyclone.aclibrary.view.others;
/*
 * Created by HoangDong on 19/09/2017.
 */

import com.appscyclone.aclibrary.view.model.ACBaseGroupModel;

import java.util.List;

public class ACExpandGroupList {
    public List<? extends ACBaseGroupModel> groups;
    public boolean[] expandedGroupIndexes;

    public ACExpandGroupList(List<? extends ACBaseGroupModel> groups,boolean isExpandAll) {
        this.groups = groups;
        expandedGroupIndexes = new boolean[groups.size()];
        for (int i = 0; i < groups.size(); i++) {
            expandedGroupIndexes[i] = isExpandAll;
        }
    }

    public ACExpandListPosition getUnflattenedPosition(int flPos) {
        int groupItemCount;
        int adapted = flPos;
        for (int i = 0; i < groups.size(); i++) {
            groupItemCount = numberOfVisibleItemsInGroup(i);
            if (adapted == 0) {
                return ACExpandListPosition.obtain(ACExpandListPosition.GROUP, i, -1, flPos);
            } else if (adapted < groupItemCount) {
                return ACExpandListPosition.obtain(ACExpandListPosition.CHILD, i, adapted - 1, flPos);
            }
            adapted -= groupItemCount;
        }
        throw new RuntimeException("Unknown state");
    }

    private int numberOfVisibleItemsInGroup(int group) {
        if (expandedGroupIndexes[group]) {
            return groups.get(group).getItemCount() + 1;
        } else {
            return 1;
        }
    }

    public ACBaseGroupModel getExpandableGroup(ACExpandListPosition listPosition) {
        return groups.get(listPosition.groupPos);
    }

    public int getVisibleItemCount() {
        int count = 0;
        for (int i = 0; i < groups.size(); i++) {
            count += numberOfVisibleItemsInGroup(i);
        }
        return count;
    }

    public int getFlattenedGroupIndex(ACExpandListPosition listPosition) {
        int groupIndex = listPosition.groupPos;
        int runningTotal = 0;

        for (int i = 0; i < groupIndex; i++) {
            runningTotal += numberOfVisibleItemsInGroup(i);
        }
        return runningTotal;
    }
}
