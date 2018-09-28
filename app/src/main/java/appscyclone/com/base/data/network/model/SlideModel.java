package appscyclone.com.base.data.network.model;

import com.google.gson.annotations.SerializedName;

/*
 * Created by NhatHoang on 28/09/2018.
 */
public class SlideModel extends BaseModel {
    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url != null ? url : "";
    }
}
