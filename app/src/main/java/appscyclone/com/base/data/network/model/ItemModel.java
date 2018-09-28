package appscyclone.com.base.data.network.model;

import android.transition.Slide;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
 * Created by NhatHoang on 06/09/2018.
 */
public class ItemModel extends BaseModel {

    @SerializedName("songTitle")
    private String title;
    @SerializedName("url")
    private String imageUrl;
    @SerializedName("slide")
    private SlideModel slideModel;

    public String getTitle() {
        return title != null ? title : "";
    }

    public String getImageUrl() {
        return imageUrl != null ? imageUrl : "";
    }

    public SlideModel getSlideModel() {
        return slideModel;
    }
}
