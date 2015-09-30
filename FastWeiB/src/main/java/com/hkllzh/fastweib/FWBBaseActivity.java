package com.hkllzh.fastweib;

import android.os.Bundle;

import com.hkllzh.android.ui.BaseActivity;
import com.hkllzh.fastweib.auth.AccessTokenKeeper;
import com.hkllzh.fastweib.view.LoadingDialog;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;


/**
 * 本项目Activity的基类
 * <p/>
 * lizheng -- 15/1/10
 * <p/>
 * com.hkllzh.fastweib
 */
public abstract class FWBBaseActivity extends BaseActivity {
    private LoadingDialog loadingDialog;
    // 微博token类
    protected Oauth2AccessToken mAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAccessToken = AccessTokenKeeper.readAccessToken(mActivity);

        loadIntentData();
        setContentView(getContentViewId());
        initView();
        initData();
        setListener();

    }

    protected void loadIntentData() {
    }

    protected void showLoading() {
        showLoading("");
    }

    protected void showLoading(String text) {
        try {
            if (null == loadingDialog) {
                loadingDialog = new LoadingDialog(mActivity);
            }

            loadingDialog.setShowText(text);

            if (!loadingDialog.isShowing()) {
                loadingDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void dismissLoading() {
        try {
            if (null != loadingDialog && loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract int getContentViewId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void setListener();

}
