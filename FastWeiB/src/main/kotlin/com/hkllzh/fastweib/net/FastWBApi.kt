package com.hkllzh.fastweib.net

import com.hkllzh.android.net.impl.AbstractApiImpl
import com.sina.weibo.sdk.auth.Oauth2AccessToken

/**
 * 项目API基类
 *
 *
 * lizheng -- 2015/08/23
 *
 *
 * FastWeiB
 */
abstract class FastWBApi : AbstractApiImpl {
    /**
     * constructor(accessToken: Oauth2AccessToken) : super(accessToken) {
    }

    constructor(mAccessToken: Oauth2AccessToken, testTwoId: String) : super(mAccessToken) {
    this.testTwoId = testTwoId
    }
     */
    protected var mAccessToken: Oauth2AccessToken ? = null
    public  var isShowLoaddingDialog = false

    constructor(accessToken: Oauth2AccessToken) : super() {
        this.mAccessToken = accessToken
    }

    constructor(accessToken: Oauth2AccessToken, isShowLoaddingDialog: Boolean) : super() {
        this.mAccessToken = accessToken
        this.isShowLoaddingDialog = isShowLoaddingDialog
    }


}
