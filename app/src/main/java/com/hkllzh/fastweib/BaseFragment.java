package com.hkllzh.fastweib;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hkllzh.fastweib.auth.AccessTokenKeeper;
import com.hkllzh.fastweib.net.NetRequest;
import com.hkllzh.fastweib.util.SPUtil;
import com.hkllzh.fastweib.view.LoadingDialog;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * 本项目的Fragment基类
 * <p/>
 * lizheng -- 15/1/27
 * <p/>
 * FastWeiB
 */
public abstract class BaseFragment extends Fragment {
    protected NetRequest netRequest;

    protected static final SPUtil spUtil;
    protected static final int W_PX;
    protected static final int H_PX;

    private BaseActivity mBaseActivity;

    private LoadingDialog loadingDialog;

    // 微博token类
    protected Oauth2AccessToken mAccessToken;

    static {
        spUtil = SPUtil.getInstance();
        W_PX = spUtil.getInt(C.SP.SCREEN_WIDTH, 0);
        H_PX = spUtil.getInt(C.SP.SCREEN_HEIGHT, 0);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof BaseActivity) {
            mBaseActivity = (BaseActivity) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getContentViewId(), container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (null == mBaseActivity) {
            loadingDialog = new LoadingDialog(getActivity());
        }

        netRequest = NetRequest.getInstance();
        mAccessToken = AccessTokenKeeper.readAccessToken(getActivity());

        initView();
        initData();
        setListener();
    }

    protected void showLoading() {
        try {
            if (null != mBaseActivity) {
                mBaseActivity.showLoading();
            } else {
                if (null != loadingDialog && !loadingDialog.isShowing()) {
                    loadingDialog.show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void dismissLoading() {
        try {
            if (null != mBaseActivity) {
                mBaseActivity.dismissLoading();
            } else {
                if (null != loadingDialog && loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
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
