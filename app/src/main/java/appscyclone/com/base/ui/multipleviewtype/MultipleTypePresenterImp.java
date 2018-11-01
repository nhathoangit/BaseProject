package appscyclone.com.base.ui.multipleview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import appscyclone.com.base.data.network.api.TestApi;
import appscyclone.com.base.data.network.model.ErrorModel;
import appscyclone.com.base.data.network.model.ItemModel;
import appscyclone.com.base.others.constant.ApiKeyParam;
import appscyclone.com.base.ui.base.BasePresenter;
import appscyclone.com.base.ui.home.HomeContract;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/*
 * Created by NhatHoang on 31/10/2018.
 */
public class MultipleTypePresenterImp extends BasePresenter<MultipleTypeContract.MultipleTypeView> implements MultipleTypeContract.MultipleTypePresenter{

    private TestApi testApi;
    private List<ItemModel> data;

    @Inject
    public MultipleTypePresenterImp(TestApi testApi) {
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

