package appscyclone.com.base.data.network.api;

import appscyclone.com.base.data.network.model.ResStoryModel;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 * Created by NhatHoang on 09/05/2018.
 */
public interface StoryApi {

    @GET("stories.php")
    Observable<ResStoryModel> getStories(@Query("page") int page);
}
