package appscyclone.com.base.network.builder;
/*
 * Created by HoangDong on 30/11/2017.
 */

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import appscyclone.com.base.bases.BaseActivity;
import appscyclone.com.base.bases.BaseModel;
import appscyclone.com.base.interfaces.ApiRequestListener;
import appscyclone.com.base.interfaces.ApiResponseListener;
import appscyclone.com.base.models.ErrorModel;
import appscyclone.com.base.utils.AppUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiBaseHandler {
    private BaseActivity mActivity;
    private ApiResponseListener mResponseListener;
    private boolean mResponseEnable=true;

    public ApiBaseHandler(BaseActivity activity, ApiResponseListener responseListener) {
        this.mActivity = activity;
        this.mResponseListener = responseListener;

    }

    public void setResponseEnable(boolean mResponseEnable)
    {
        this.mResponseEnable=mResponseEnable;
    }

    public ApiRequestListener requestListener = (nCode, nType, call) -> {
        boolean isNetwork = AppUtils.isNetworkAvailable(mActivity);
        if (call != null && isNetwork) {
            if (call.isExecuted()) {
                call.cancel();
            }
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                    BaseModel mData = null;
                    if (response.body() != null) {
                        Gson gson = new Gson();
                        mData = gson.fromJson(response.body(), nType);
                    }
                    if(!mResponseEnable)
                        return;
                    onDataResponse(nCode, mData);
                }

                @Override
                public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                    if(!mResponseEnable)
                        return;
                    mResponseListener.onDataError(nCode, ErrorModel.parseData(t.getMessage()));
                    //mActivity.dismissLoading();
                }
            });
        } else {
           //if (!isNetwork)
           //    AppUtils.showAlert(mActivity, mActivity.getString(R.string.error_network),
           //            mActivity.getString(R.string.error_no_internet), null);
        }
    };

    private void onDataResponse(int nCode, BaseModel nData) {
        mResponseListener.onDataResponse(nCode, nData);
    }
}
