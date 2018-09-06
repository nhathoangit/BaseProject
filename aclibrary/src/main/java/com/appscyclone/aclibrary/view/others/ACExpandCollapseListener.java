package com.appscyclone.aclibrary.view.others;
/*
 * Created by HoangDong on 20/09/2017.
 */

public interface ACExpandCollapseListener {
    void onGroupExpanded(int positionStart, int itemCount);

    void onGroupCollapsed(int positionStart, int itemCount);
}
