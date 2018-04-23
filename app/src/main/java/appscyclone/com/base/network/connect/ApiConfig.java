package appscyclone.com.base.network.connect;
/*
 * Created by HoangDong on 30/11/2017.
 */

public class ApiConfig {
    public static ApiConfigDetail createConnectionDetail(ApiConfigType type) {
        ApiConfigDetail connection = new ApiConfigDetail();
        if (type == null) {
            type = ApiConfigType.DEV;
        }
        switch (type) {
            case DEV:
                connection.setBaseURL("https://api.github.com/");
                break;
            case STAGING:
                connection.setBaseURL("http://34.206.152.127/api/");
                break;
            case PRODUCTION:
                connection.setBaseURL("https://www.swoppler.com/api/");
                break;
            default:
                break;
        }
        return connection;
    }
}
