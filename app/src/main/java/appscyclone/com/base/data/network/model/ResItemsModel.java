package appscyclone.com.base.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
 * Created by NhatHoang on 06/09/2018.
 */
public class ResItemsModel extends BaseModel {
    @SerializedName("items")
    public List<ItemModel> items;
    @SerializedName("total")
    private Integer total;

    public Integer getTotal() {
        return total;
    }
}
