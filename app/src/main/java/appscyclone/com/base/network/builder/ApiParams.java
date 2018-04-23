package appscyclone.com.base.network.builder;
/*
 * Created by HoangDong on 30/11/2017.
 */

import java.util.HashMap;
import java.util.Map;

public class ApiParams {

    private Map<String, Object> mParams = new HashMap<>();
    public void add(String key, Object value) {
        mParams.put(key, value);
    }
    public Map<String, Object> getParams() {
        return mParams;
    }
}
