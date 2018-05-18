package appscyclone.com.base.dagger.connect;
/*
 * Created by HoangDong on 08/11/2017.
 */

public enum ApiConfigType {
    STAGING("staging"),
    DEVELOP("develop"),
    PRELIVE("prelive"),
    LIVE("live");

    private String mTitle;

    ApiConfigType(String title) {
        this.mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }
}
