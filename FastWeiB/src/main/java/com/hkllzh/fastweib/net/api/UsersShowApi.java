package com.hkllzh.fastweib.net.api;

import com.hkllzh.android.net.RequestParams;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * 获取用户信息
 * <pre>
 * 网页地址：http://open.weibo.com/wiki/2/users/show
 * 接口地址：https://api.weibo.com/2/users/show.json
 * 请求方式：GET
 * 参数：
 * source       false	string	采用OAuth授权方式不需要此参数，其他授权方式为必填参数，数值为应用的AppKey。
 * access_token false	string	采用OAuth授权方式为必填参数，其他授权方式不需要此参数，OAuth授权后获得。
 * uid          false	int64	需要查询的用户ID。
 * screen_name  false	string	需要查询的用户昵称。
 * </pre>
 * <p/>
 * lizheng--2015/08/23
 * <p/>
 * FastWeiB
 */

public class UsersShowApi extends FastWBApi {

    public UsersShowApi(Oauth2AccessToken accessToken) {
        super(accessToken);
    }

    @Override
    public String requestURL() {
        return "https://api.weibo.com/2/users/show.json";
    }

    @Override
    public RequestMethod requestMethod() {
        return RequestMethod.GET;
    }

    @Override
    public RequestParams requestParams() {
        RequestParams params = new RequestParams();
        params.put("access_token", mAccessToken.getToken());
        params.put("uid", mAccessToken.getUid());
        return params;
    }
}
