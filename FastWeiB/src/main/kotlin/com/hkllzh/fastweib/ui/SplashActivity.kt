package com.hkllzh.fastweib.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.hkllzh.fastweib.C
import com.hkllzh.fastweib.FWBBaseActivity
import com.hkllzh.fastweib.R
import com.hkllzh.fastweib.auth.AccessTokenKeeper
import com.sina.weibo.sdk.auth.AuthInfo
import com.sina.weibo.sdk.auth.Oauth2AccessToken
import com.sina.weibo.sdk.auth.WeiboAuthListener
import com.sina.weibo.sdk.auth.sso.SsoHandler
import com.sina.weibo.sdk.exception.WeiboException

/**
 * 启动页面
 *
 *
 * 检查微博授权，没有授权提示授权，有授权进入首页
 *
 *
 * lizheng -- 15/1/11
 *
 *
 * FastWeiB
 */
public class SplashActivity : FWBBaseActivity() {
    private var tvTips: TextView? = null
    private var btnAuth: Button? = null

    override fun getContentViewId(): Int {
        return R.layout.ac_splash
    }

    override fun initView() {
        tvTips = findViewById(R.id.tvTips) as TextView
        btnAuth = findViewById(R.id.btnAuth) as Button
    }

    override fun initData() {
        if (!mAccessToken.isSessionValid) {
            //  AccessToken是否有效
            tvTips!!.visibility = View.GONE
            btnAuth!!.visibility = View.VISIBLE
        } else {
            // 进入主程序
            Handler().postDelayed(object : Runnable {
                override fun run() {
                    log.e("SplashActivity", "// 进入主程序// 进入主程序// 进入主程序")
                    startActivity(Intent(mActivity, IndexActivity::class.java))
                    finish()
                    // startActivity(new Intent(mActivity, TestWebViewActivity.class));

                }
            }, 1500)
        }


        // demo 1
        var list = listOf(1, 123, 4, 32, 4, 12, 4, 324, 23, 4, 324, 32, 41)
        for ((k, v) in list.withIndex()) {
            log.i("SplashActivity", "k->$k, v->$v")
        }

        // demo 2
        testLambdas();


    }

    private fun testLambdas() {
        var sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }

        var three = sum(1, 2)

        fun apply(i: Int, f: (Int) -> Int) = f(i)

        apply(2, { x -> x + 25 })

        apply(2) { x -> x + 123 }

    }

    override fun setListener() {

    }

    /**
     * 授权按钮点击事件

     * @param view clickView
     */
    public fun btnClickAuth(view: View) {
        val mAuthInfo = AuthInfo(this, C.WeiBo.APP_KEY, C.WeiBo.REDIRECT_URL, C.WeiBo.SCOPE)
        val mSsoHandler = SsoHandler(mActivity, mAuthInfo)
        mSsoHandler.authorizeWeb(AuthListener())
    }


    /**
     * 微博认证授权回调类。
     * 1. SSO 授权时，需要在 [.onActivityResult] 中调用 [SsoHandler.authorizeCallBack] 后，
     * 该回调才会被执行。
     * 2. 非 SSO 授权时，当授权结束后，该回调就会被执行。
     * 当授权成功后，请保存该 access_token、expires_in、uid 等信息到 SharedPreferences 中。
     */
    inner class AuthListener : WeiboAuthListener {

        override fun onComplete(values: Bundle) {
            // 从 Bundle 中解析 Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(values)
            if (mAccessToken.isSessionValid) {

                // 保存 Token 到 SharedPreferences
                AccessTokenKeeper.writeAccessToken(mActivity, mAccessToken)
                Toast.makeText(mActivity,
                        R.string.weibosdk_demo_toast_auth_success, Toast.LENGTH_SHORT).show()


                startActivity(Intent(mActivity, IndexActivity::class.java))
                finish()

            } else {
                // 以下几种情况，您会收到 Code：
                // 1. 当您未在平台上注册的应用程序的包名与签名时；
                // 2. 当您注册的应用程序包名与签名不正确时；
                // 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
                val code = values.getString("code")
                var message = getString(R.string.weibosdk_demo_toast_auth_failed)
                if (!TextUtils.isEmpty(code)) {
                    message = message + "\nObtained the code: " + code
                }
                Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show()
            }
        }

        override fun onCancel() {
            Toast.makeText(mActivity,
                    R.string.weibosdk_demo_toast_auth_canceled, Toast.LENGTH_LONG).show()
        }

        override fun onWeiboException(e: WeiboException) {
            Toast.makeText(mActivity,
                    "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show()
        }
    }
}
