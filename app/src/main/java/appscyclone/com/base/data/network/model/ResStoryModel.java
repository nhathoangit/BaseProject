package appscyclone.com.base.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by NhatHoang on 09/05/2018.
 */
public class ResStoryModel extends BaseModel {
    @SerializedName("stories")
    public List<StoryModel> stories = new ArrayList<>();
}
