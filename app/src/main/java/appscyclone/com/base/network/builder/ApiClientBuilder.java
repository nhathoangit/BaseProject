package appscyclone.com.base.network.builder;
/*
 * Created by HoangDong on 30/11/2017.
 */

import android.text.TextUtils;

import com.google.gson.JsonObject;

import java.lang.reflect.Type;

import appscyclone.com.base.bases.BaseApplication;
import appscyclone.com.base.config.AppConfig;
import appscyclone.com.base.interfaces.ApiRequestListener;
import appscyclone.com.base.network.connect.ApiConfig;
import appscyclone.com.base.others.constant.ApiKeyParam;
import retrofit2.Call;

abstract class ApiClientBuilder {
    private ApiRequestListener mListener;
    private String mApiKey;
    private BaseApplication baseApp;

    ApiClientBuilder(ApiRequestListener listener, BaseApplication baseApp) {
        this.mListener = listener;
        this.baseApp = baseApp;
    }

    void requestApi(Type nType, Call<JsonObject> call) {
        if (mListener != null) {
            mListener.onRequestApi(ApiKeyParam.CODE_DEFAULT, nType, call);
        }
    }

    void requestApi(int nCode, Type nType, Call<JsonObject> call) {
        if (mListener != null) {
            mListener.onRequestApi(nCode, nType, call);
        }
    }

    void addApiKey(ApiParams params) {
        if (TextUtils.isEmpty(mApiKey))
            mApiKey = ApiConfig.createConnectionDetail(AppConfig.mConnectType).getApiKey();
        params.add(ApiKeyParam.API_KEY, mApiKey);
    }

//    String getToken() {
//        return baseApp != null ? baseApp.getToken() : "";
//    }
}
