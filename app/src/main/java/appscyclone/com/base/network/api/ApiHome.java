package appscyclone.com.base.network.api;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/*
 * Created by NhatHoang on 20/04/2018.
 */
public interface ApiHome {
    @GET("/users/{login}")
    Call<JsonObject> getUser(@Path("login") String login);
}
