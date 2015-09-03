package com.hkllzh.fastweib.net;

import com.hkllzh.android.net.okhttp.OkHttpRequest;
import com.squareup.okhttp.OkHttpClient;

/**
 * FastWeiB Request
 * <p/>
 * lizheng -- 2015/08/23
 * <p/>
 * FastWeiB
 */
public class FastWBRequest extends OkHttpRequest {

    private static FastWBRequest ourInstance = new FastWBRequest();
    private OkHttpClient okHttpClient;

    public static FastWBRequest getInstance() {
        return ourInstance;
    }

    private FastWBRequest() {
        okHttpClient = new OkHttpClient();
    }

    @Override
    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

}
