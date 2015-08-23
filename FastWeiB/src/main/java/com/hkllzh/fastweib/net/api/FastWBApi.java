package com.hkllzh.fastweib.net.api;

import com.hkllzh.android.net.AbstractApi;
import com.hkllzh.android.net.RequestInterface;
import com.hkllzh.fastweib.net.FastWBRequest;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * 项目API基类
 * <p/>
 * lizheng -- 2015/08/23
 * <p/>
 * FastWeiB
 */
public abstract class FastWBApi extends AbstractApi {

    protected  Oauth2AccessToken mAccessToken;

    public FastWBApi(Oauth2AccessToken accessToken) {
        this.mAccessToken = accessToken;
    }

    @Override
    public RequestInterface getBaseRequest() {
        return FastWBRequest.getInstance();
    }
}
