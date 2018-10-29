package appscyclone.com.base.ui.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import appscyclone.com.base.data.network.api.TestApi;
import appscyclone.com.base.data.network.model.ErrorModel;
import appscyclone.com.base.data.network.model.ItemModel;
import appscyclone.com.base.others.constant.ApiKeyParam;
import appscyclone.com.base.ui.base.BaseApplication;
import appscyclone.com.base.ui.base.BasePresenter;
import appscyclone.com.base.ui.main.MainContract;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/*
 * Created by NhatHoang on 07/09/2018.
 */
public class HomePresenterImp extends BasePresenter<HomeContract.HomeView> implements HomeContract.HomePresenter{

    private TestApi testApi;
    private List<ItemModel> data;

    @Inject
    public HomePresenterImp(TestApi testApi) {
        this.testApi = testApi;
        data= new ArrayList<>();
    }

    @Override
    public void getListItems(int page) {
        Map<String, Object> mMapParams = new HashMap<>();
        mMapParams.put(ApiKeyParam.PAGE, page);
        mMapParams.put(ApiKeyParam.PER_PAGE, 10);
        addSubscribe(testApi.getDataTest(mMapParams).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(resData -> {
            getView().hideLoading();
            if(data.size() <= 0){
                data.addAll(resData.items);
                getView().loadListItems(data,resData.getTotal());
            }else {
                getView().updateListItems(data, resData);
            }
        }, throwable -> {
            getView().hideLoading();
            ErrorModel errorModel = ErrorModel.parseData(throwable.getMessage());
            getView().loadDataError(errorModel.getMessage());
        }));
    }

}
