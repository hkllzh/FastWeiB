package com.hkllzh.fastweib;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hkllzh.android.ui.BaseFragment;
import com.hkllzh.fastweib.auth.AccessTokenKeeper;
import com.hkllzh.fastweib.view.LoadingDialog;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * 本项目的Fragment基类
 * <p/>
 * lizheng -- 15/1/27
 * <p/>
 * FastWeiB
 */
public abstract class FWBBaseFragment extends BaseFragment {

    private FWBBaseActivity mBaseActivity;

    private LoadingDialog loadingDialog;

    // 微博token类
    protected Oauth2AccessToken mAccessToken;

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        if (activity instanceof FWBBaseActivity) {
            mBaseActivity = (FWBBaseActivity) activity;
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

    protected <T extends View> T findViewById(@IdRes int id) {
        return (T) getView().findViewById(id);
    }

    public abstract int getContentViewId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void setListener();
}
