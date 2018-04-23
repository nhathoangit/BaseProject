package appscyclone.com.base.network.builder;

import com.google.gson.reflect.TypeToken;

import appscyclone.com.base.bases.BaseApplication;
import appscyclone.com.base.interfaces.ApiRequestListener;
import appscyclone.com.base.models.UserModel;
import appscyclone.com.base.network.api.ApiHome;

/*
 * Created by NhatHoang on 20/04/2018.
 */
public class ApiClient extends ApiClientBuilder {

    public ApiClient(ApiRequestListener listener, BaseApplication baseApp) {
        super(listener, baseApp);
    }

    public void getMessageNotification(String login) {
        ApiParams params = new ApiParams();
        //if (!TextUtils.isEmpty(getToken()))
        //    params.add(ApiKeyParam.AUTH_TOKEN, getToken());
        ApiHome api = ApiGenerator.getInstance().createService(ApiHome.class);
        requestApi(new TypeToken<UserModel>() {
        }.getType(), api.getUser(login));

    }
}
