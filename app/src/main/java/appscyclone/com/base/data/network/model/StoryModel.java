package appscyclone.com.base.data.network.model;

import com.google.gson.annotations.SerializedName;

/*
 * Created by NhatHoang on 09/05/2018.
 */
public class StoryModel extends BaseModel {
    @SerializedName("title")
    private String title;
    @SerializedName("link")
    private String link;
    @SerializedName("image")
    private String image;
    @SerializedName("desc")
    private String desc;
    @SerializedName("view")
    private String view;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getImage() {
        return image;
    }

    public String getDesc() {
        return desc;
    }

    public String getView() {
        return view;
    }
}
