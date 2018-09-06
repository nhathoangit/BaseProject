package com.appscyclone.aclibrary.view.model;
/*
 * Created by HoangDong on 19/09/2017.
 */

import java.util.ArrayList;
import java.util.List;

public class ACBaseGroupModel<T> {
    private String titleParent;
    private List<T> child;

    public ACBaseGroupModel(String title, List<T> child) {
        this.titleParent = title;
        this.child = child;
    }
    public ACBaseGroupModel() {}

    public void setChild(List<T> child)
    {
        this.child=child;
    }

    public void setChild(T item)
    {
        if(child==null)
            child=new ArrayList<T>();
        child.add(item);
    }
    protected void setChild(){};

    public String getTitle() {
        return titleParent;
    }

    public List<T> getItems() {
        return child;
    }

    public int getItemCount() {
        if(child==null)
            setChild();
        return child == null ? 0 : child.size();
    }
}
