package appscyclone.com.base.data.local;
/*
 * Created by HoangDong on 01/12/2017.
 */

import androidx.annotation.IdRes;

public class CustomTransaction {
    @IdRes
    public int containerViewId;
    public boolean isAnimation;

    public CustomTransaction() {
    }

    public CustomTransaction(@IdRes int containerViewId, boolean isAnimation) {
        this.containerViewId = containerViewId;
        this.isAnimation = isAnimation;
    }

    public CustomTransaction(boolean isAnimation) {
        this.isAnimation = isAnimation;
    }

    public CustomTransaction(@IdRes int containerViewId) {
        this.containerViewId = containerViewId;
    }
}
