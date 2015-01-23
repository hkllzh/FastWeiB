package com.hkllzh.fastweib.net;

import com.loopj.android.http.RequestParams;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * 专用于微博数据请求的地址和参数封装类
 * <p/>
 * lizheng -- 15/1/23
 * <p/>
 * FastWeiB
 */
public final class UrlParams {
    private UrlParams() {
    }

    /**
     * <pre>
     * source       false	string	采用OAuth授权方式不需要此参数，其他授权方式为必填参数，数值为应用的AppKey。
     * access_token false	string	采用OAuth授权方式为必填参数，其他授权方式不需要此参数，OAuth授权后获得。
     * uid          false	int64	需要查询的用户ID。
     * screen_name  false	string	需要查询的用户昵称。*
     * </pre>
     * @return
     */
    public static UrlParamsBean UsersShow(Oauth2AccessToken accessToken) {
        RequestParams params = new RequestParams();
        params.add("access_token", accessToken.getToken());
        params.add("uid", accessToken.getUid());
        return new UrlParamsBean(Apis.Users_Show, params);
    }


}
