package com.hkllzh.fastweib.net.api

import com.hkllzh.android.net.APIInterface
import com.hkllzh.android.net.RequestParams
import com.hkllzh.fastweib.net.FastWBApi
import com.sina.weibo.sdk.auth.Oauth2AccessToken

/**
 * 获取用户信息
 *
 * 网页地址：http://open.weibo.com/wiki/2/users/show
 * 接口地址：https://api.weibo.com/2/users/show.json
 * 请求方式：GET
 * 参数：
 * source       false	string	采用OAuth授权方式不需要此参数，其他授权方式为必填参数，数值为应用的AppKey。
 * access_token false	string	采用OAuth授权方式为必填参数，其他授权方式不需要此参数，OAuth授权后获得。
 * uid          false	int64	需要查询的用户ID。
 * screen_name  false	string	需要查询的用户昵称。
 *
 *
 *
 * lizheng--2015/08/23
 *
 *
 * FastWeiB
 */

class UsersShowApi : FastWBApi {

    var testTwoId: String? = null

    constructor(accessToken: Oauth2AccessToken) : super(accessToken) {
    }

    constructor(mAccessToken: Oauth2AccessToken, testTwoId: String) : super(mAccessToken) {
        this.testTwoId = testTwoId
    }

    override fun requestURL(): String {
        return "https://api.weibo.com/2/users/show.json"
    }

    override fun requestMethod(): APIInterface.RequestMethod {
        return APIInterface.RequestMethod.GET
    }

    override fun requestParams(): RequestParams {
        val params = RequestParams()
        params.put("access_token", mAccessToken?.token)
        params.put("uid", mAccessToken?.uid)
        return params
    }
}
