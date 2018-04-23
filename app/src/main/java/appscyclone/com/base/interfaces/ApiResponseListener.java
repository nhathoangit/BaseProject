package appscyclone.com.base.interfaces;
/*
 * Created by HoangDong on 30/11/2017.
 */


import appscyclone.com.base.bases.BaseModel;
import appscyclone.com.base.models.ErrorModel;

public interface ApiResponseListener {
    void onDataError(int nCode, ErrorModel t);

    void onDataResponse(int nCode, BaseModel nData);
}
