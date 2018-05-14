package appscyclone.com.base.ui.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/*
 * Created by NhatHoang on 14/05/2018.
 */
public class BasePresenter<V extends MvpView> implements IPresenter<V> {

    private V view;
    private CompositeDisposable compositeDisposable;

    @Override
    public void onAttach(V mvpView) {
        this.view = mvpView;
    }

    @Override
    public void onDetach() {
        this.view = null;
        unsubscribe();
    }

    @Override
    public boolean isViewAttach() {
        return view != null;
    }

    private void unsubscribe() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    public V getView() {
        return view;
    }

    protected void addSubscribe(Disposable disposable) {
        compositeDisposable.add(disposable);
    }
}
