package com.hkllzh.fastweib.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hkllzh.fastweib.BaseActivity;
import com.hkllzh.fastweib.C;
import com.hkllzh.fastweib.R;
import com.hkllzh.fastweib.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

/**
 * 启动页面
 * <p/>
 * 检查微博授权，没有授权提示授权，有授权进入首页
 * <p/>
 * lizheng -- 15/1/11
 * <p/>
 * FastWeiB
 */
public class SplashActivity extends BaseActivity {
    private TextView tvTips;
    private Button btnAuth;

    @Override
    public int getContentViewId() {
        return R.layout.ac_splash;
    }

    @Override
    protected void initView() {
        tvTips = (TextView) findViewById(R.id.tvTips);
        btnAuth = (Button) findViewById(R.id.btnAuth);
    }

    @Override
    protected void initData() {
        if (!mAccessToken.isSessionValid()) { //  AccessToken是否有效
            tvTips.setVisibility(View.GONE);
            btnAuth.setVisibility(View.VISIBLE);
        } else {
            // 进入主程序
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(mActivity, IndexActivity.class));
                    finish();
                }
            }, 1500);
        }
    }

    @Override
    protected void setListener() {

    }

    /**
     * 授权按钮点击事件
     *
     * @param view clickView
     */
    public void btnClickAuth(View view) {
        AuthInfo mAuthInfo = new AuthInfo(this, C.WeiBo.APP_KEY, C.WeiBo.REDIRECT_URL, C.WeiBo.SCOPE);
        SsoHandler mSsoHandler = new SsoHandler(mActivity, mAuthInfo);
        mSsoHandler.authorizeWeb(new AuthListener());
    }


    /**
     * 微博认证授权回调类。
     * 1. SSO 授权时，需要在 {@link #onActivityResult} 中调用 {@link SsoHandler#authorizeCallBack} 后，
     * 该回调才会被执行。
     * 2. 非 SSO 授权时，当授权结束后，该回调就会被执行。
     * 当授权成功后，请保存该 access_token、expires_in、uid 等信息到 SharedPreferences 中。
     */
    class AuthListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle values) {
            // 从 Bundle 中解析 Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            if (mAccessToken.isSessionValid()) {

                // 保存 Token 到 SharedPreferences
                AccessTokenKeeper.writeAccessToken(mActivity, mAccessToken);
                Toast.makeText(mActivity,
                        R.string.weibosdk_demo_toast_auth_success, Toast.LENGTH_SHORT).show();


                startActivity(new Intent(mActivity, IndexActivity.class));
                finish();
                
            } else {
                // 以下几种情况，您会收到 Code：
                // 1. 当您未在平台上注册的应用程序的包名与签名时；
                // 2. 当您注册的应用程序包名与签名不正确时；
                // 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
                String code = values.getString("code");
                String message = getString(R.string.weibosdk_demo_toast_auth_failed);
                if (!TextUtils.isEmpty(code)) {
                    message = message + "\nObtained the code: " + code;
                }
                Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancel() {
            Toast.makeText(mActivity,
                    R.string.weibosdk_demo_toast_auth_canceled, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Toast.makeText(mActivity,
                    "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
