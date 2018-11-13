package appscyclone.com.base.ui.expandview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.appscyclone.aclibrary.view.ACRecyclerView;
import com.appscyclone.aclibrary.view.adapter.ACExpandBaseAdapter;
import com.appscyclone.aclibrary.view.model.ACBaseGroupModel;

import java.util.List;

import appscyclone.com.base.R;

/*
 * Created by NhatHoang on 13/11/2018.
 */
public class ExpandAdapter extends ACExpandBaseAdapter<ExpandAdapter.ParentViewHolder, ExpandAdapter.ChildViewHolder> {

    public ExpandAdapter(List<? extends ACBaseGroupModel> groups, boolean isExpandAll) {
        super(groups, isExpandAll);
    }

    @Override
    public ParentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expand_parent, parent, false);
        return new ExpandAdapter.ParentViewHolder(view);
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expand_child, parent, false);
        return new ExpandAdapter.ChildViewHolder(view);
    }

    @Override
    public void onBindGroupViewHolder(ParentViewHolder holder, int flatPosition, ACBaseGroupModel group) {
       if(isGroupExpanded(group)){
           holder.imageView.setRotation(180);
       }else
           holder.imageView.setRotation(360);

    }

    @Override
    public void onBindChildViewHolder(ChildViewHolder holder, int flatPosition, ACBaseGroupModel group, int childIndex) {

    }

    class ParentViewHolder extends ACRecyclerView.ACParentViewHolder {
        private ImageView imageView;

        ParentViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.list_item_genre_arrow);
        }

        @Override
        public void bindData(Object data) {
        }

        @Override
        public View getViewRotate() {
            return imageView;
        }

    }

    class ChildViewHolder extends ACRecyclerView.ACChildViewHolder {

        ChildViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(Object data) {

        }

    }

}
