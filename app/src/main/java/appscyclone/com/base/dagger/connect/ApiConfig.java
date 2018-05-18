package appscyclone.com.base.dagger.connect;
/*
 * Created by HoangDong on 08/11/2017.
 */

public class ApiConfig {
    public static ApiConfigDetail createConnectionDetail(ApiConfigType type) {
        ApiConfigDetail connection = new ApiConfigDetail();
        if (type == null) {
            type = ApiConfigType.DEVELOP;
        }
        switch (type) {
            case DEVELOP:
                connection.setBaseURL("https://api.themoviedb.org/3/");
                break;
            case STAGING:
                connection.setBaseURL("http://windhost.esy.es/");
                break;
            case PRELIVE:
                connection.setBaseURL("https://prelive.fxchange.rmlbs.co/api/v1/");
                connection.setmApiKey("DEVACCRMLEXCHANGE03052017");
                break;
            case LIVE:
                connection.setBaseURL("https://live.fxchange.sg/api/v1/");
                connection.setmApiKey("LIVEACCRMLEXCHANGE03032017");
                break;
            default:
                break;
        }
        return connection;
    }
}
