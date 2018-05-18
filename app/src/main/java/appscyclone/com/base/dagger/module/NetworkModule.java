package appscyclone.com.base.dagger.module;

import android.app.Application;
import android.support.annotation.NonNull;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import appscyclone.com.base.dagger.connect.ApiConfig;
import appscyclone.com.base.dagger.connect.ApiConfigType;
import appscyclone.com.base.data.network.api.StoryApi;
import appscyclone.com.base.data.network.model.ErrorModel;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * Created by NhatHoang on 15/05/2018.
 */
@Module
public class NetworkModule {
    private static final long DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB
    private ApiConfigType connectType;

    public NetworkModule(ApiConfigType connectType) {
        this.connectType = connectType;
    }

    @Provides
    public OkHttpClient provideOkHttpClient(Application app) {
        File cacheDir = new File(app.getCacheDir(), "http");
        return new OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .cache(new okhttp3.Cache(cacheDir, DISK_CACHE_SIZE))
                .build();
    }

    @Provides
    public Retrofit createRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(4, TimeUnit.MINUTES)
                .writeTimeout(4, TimeUnit.MINUTES)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        httpClient.addInterceptor(new ApiCustomInterceptor());
        return new Retrofit.Builder()
                .baseUrl(ApiConfig.createConnectionDetail(connectType).getBaseURL())
                .client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public StoryApi provideStoryApi(Retrofit restAdapter) {
        return restAdapter.create(StoryApi.class);
    }

    @SuppressWarnings("ConstantConditions")
    public static class ApiCustomInterceptor implements Interceptor {
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Response response = chain.proceed(chain.request());
            if (response.code() >= 400) {
                throw new IOException(ErrorModel.getErrorString(response));
            }
            String stringResponse = response.body().string();
            String newString = "";
            try {
                JSONObject root = new JSONObject(stringResponse);
                if (root.has("error")) {
                    throw new IOException(root.getString("error"));
                }

                newString = root.has("data") ? root.opt("data").toString() : stringResponse;
                if (response.request().url().toString().contains("v2/notifications?"))
                    newString = stringResponse;
                return response.newBuilder()
                        .message("Successful")
                        .body(ResponseBody.create(response.body().contentType(),
                                newString.startsWith("[") ? stringResponse : newString))
                        .build();
            } catch (Exception e) {
                throw new IOException(e.getMessage());
            }
        }
    }
}
