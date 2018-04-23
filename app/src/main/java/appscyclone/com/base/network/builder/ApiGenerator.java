package appscyclone.com.base.network.builder;
/*
 * Created by HoangDong on 30/11/2017.
 */

import android.support.annotation.NonNull;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import appscyclone.com.base.config.AppConfig;
import appscyclone.com.base.models.ErrorModel;
import appscyclone.com.base.network.connect.ApiConfig;
import appscyclone.com.base.network.connect.ApiConfigType;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiGenerator {

    private static ApiGenerator mInstance = new ApiGenerator();
    private Retrofit mRetrofit;

    public static synchronized ApiGenerator getInstance() {
        if (mInstance == null)
            mInstance = new ApiGenerator();
        return mInstance;
    }

    private ApiGenerator() {
        mRetrofit = createRetrofit(AppConfig.mConnectType);
    }

    private Retrofit createRetrofit(ApiConfigType connectType) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(4, TimeUnit.MINUTES)
                .writeTimeout(4, TimeUnit.MINUTES)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        httpClient.addInterceptor(new ApiCustomInterceptor());
        return new Retrofit.Builder()
                .baseUrl(ApiConfig.createConnectionDetail(connectType).getBaseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    public <S> S createService(Class<S> serviceClass) {
        return mRetrofit.create(serviceClass);
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

                newString = root.has("data")?root.opt("data").toString():stringResponse;
                if(response.request().url().toString().contains("v2/notifications?"))
                    newString=stringResponse;
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
