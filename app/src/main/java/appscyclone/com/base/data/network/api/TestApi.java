package appscyclone.com.base.data.network.api;

import java.util.Map;

import appscyclone.com.base.data.network.model.ResItemsModel;
import appscyclone.com.base.data.network.model.ResStoryModel;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/*
 * Created by NhatHoang on 06/09/2018.
 */
public interface TestApi {
    @GET("cityWorship")
    Observable<ResItemsModel> getDataTest(@QueryMap Map<String, Object> body);
}
