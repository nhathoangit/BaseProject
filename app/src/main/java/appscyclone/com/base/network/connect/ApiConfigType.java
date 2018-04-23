package appscyclone.com.base.network.connect;
/*
 * Created by HoangDong on 30/11/2017.
 */

public enum ApiConfigType {
    DEV("dev"),
    STAGING("staging"),
    PRODUCTION("production");

    private String mTitle;

    ApiConfigType(String title) {
        this.mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }
}
