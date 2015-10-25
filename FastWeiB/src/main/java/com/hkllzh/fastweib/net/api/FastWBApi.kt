package com.hkllzh.fastweib.net.api

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
abstract class FastWBApi(protected var mAccessToken: Oauth2AccessToken) : AbstractApiImpl()
