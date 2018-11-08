package appscyclone.com.base.data.local;
/*
 * Created by HoangDong on 20/09/2017.
 */

import com.appscyclone.aclibrary.view.model.ACBaseGroupModel;

import java.util.List;

public class ExpandModel extends ACBaseGroupModel<String> {

    public ExpandModel(String title, List<String> child) {
        super(title, child);
    }
}
