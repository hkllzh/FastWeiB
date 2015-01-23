package com.hkllzh.fastweib.net;

import com.loopj.android.http.RequestParams;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * 微博数据接口常量类
 * <p/>
 * lizheng -- 15/1/23
 * <p/>
 * FastWeiB
 */
public final class WeiBoApi {
    private WeiBoApi() {
    }

    /**
     * 获取用户信息
     * <pre>
     * 网页地址：http://open.weibo.com/wiki/2/users/show
     * 接口地址：https://api.weibo.com/2/users/show.json
     * 参数：
     * source       false	string	采用OAuth授权方式不需要此参数，其他授权方式为必填参数，数值为应用的AppKey。
     * access_token false	string	采用OAuth授权方式为必填参数，其他授权方式不需要此参数，OAuth授权后获得。
     * uid          false	int64	需要查询的用户ID。
     * screen_name  false	string	需要查询的用户昵称。
     * </pre>
     *
     * @return
     */
    public static UrlParamsBean UsersShow(Oauth2AccessToken accessToken) {
        RequestParams params = new RequestParams();
        params.add("access_token", accessToken.getToken());
        params.add("uid", accessToken.getUid());
        return new UrlParamsBean("https://api.weibo.com/2/users/show.json", params);
    }
}
