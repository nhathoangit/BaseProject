package appscyclone.com.base.models;

import com.google.gson.annotations.SerializedName;

import appscyclone.com.base.bases.BaseModel;

/*
 * Created by NhatHoang on 20/04/2018.
 */
public class UserModel extends BaseModel{
    @SerializedName("login")
    private String login;
    @SerializedName("id")
    private Integer id;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("gravatar_id")
    private String gravatarId;
    @SerializedName("url")
    private String url;

    public String getLogin() {
        return login;
    }

    public Integer getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public String getUrl() {
        return url;
    }
}
