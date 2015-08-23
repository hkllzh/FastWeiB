package com.hkllzh.fastweib;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;

import com.hkllzh.fastweib.auth.AccessTokenKeeper;
import com.hkllzh.fastweib.util.SPUtil;
import com.hkllzh.fastweib.view.LoadingDialog;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;


/**
 * 本项目Activity的基类
 * <p/>
 * lizheng -- 15/1/10
 * <p/>
 * com.hkllzh.fastweib
 */
public abstract class BaseActivity extends ActionBarActivity {

    protected static final SPUtil spUtil;
    protected static final int W_PX;
    protected static final int H_PX;

    protected Activity mActivity;

    private LoadingDialog loadingDialog;

    // 微博token类
    protected Oauth2AccessToken mAccessToken;

    static {
        spUtil = SPUtil.getInstance();
        W_PX = spUtil.getInt(C.SP.SCREEN_WIDTH, 0);
        H_PX = spUtil.getInt(C.SP.SCREEN_HEIGHT, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mAccessToken = AccessTokenKeeper.readAccessToken(mActivity);

        setContentView(getContentViewId());
        initView();
        initData();
        setListener();

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
