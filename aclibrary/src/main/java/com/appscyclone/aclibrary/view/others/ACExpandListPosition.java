package com.appscyclone.aclibrary.view.others;
/*
 * Created by HoangDong on 20/09/2017.
 */

import java.util.ArrayList;

public class ACExpandListPosition {
    public final static int CHILD = 1;
    public final static int GROUP = 2;


    private static final int MAX_POOL_SIZE = 5;
    private static ArrayList<ACExpandListPosition> sPool =
            new ArrayList<>(MAX_POOL_SIZE);
    public int type;
    public int groupPos;
    public int childPos;
    int flatListPos;


    public static ACExpandListPosition obtain(int type, int groupPos, int childPos,
                                              int flatListPos) {
        ACExpandListPosition elp = getRecycledOrCreate();
        elp.type = type;
        elp.groupPos = groupPos;
        elp.childPos = childPos;
        elp.flatListPos = flatListPos;
        return elp;
    }

    private static ACExpandListPosition getRecycledOrCreate() {
        ACExpandListPosition elp;
        synchronized (sPool) {
            if (sPool.size() > 0) {
                elp = sPool.remove(0);
            } else {
                return new ACExpandListPosition();
            }
        }
        elp.resetState();
        return elp;
    }

    private void resetState() {
        groupPos = 0;
        childPos = 0;
        flatListPos = 0;
        type = 0;
    }
}
