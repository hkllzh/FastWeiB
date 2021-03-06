package com.hkllzh.fastweib;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hkllzh.android.net.APIInterface;
import com.hkllzh.android.net.ResponseInterface;
import com.hkllzh.android.ui.BaseFragment;
import com.hkllzh.fastweib.auth.AccessTokenKeeper;
import com.hkllzh.fastweib.net.FastWBApi;
import com.hkllzh.fastweib.view.LoadingDialog;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * 本项目的Fragment基类
 * <p/>
 * lizheng -- 15/1/27
 * <p/>
 * FastWeiB
 */
public abstract class FWBBaseFragment extends BaseFragment implements ResponseInterface{

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

    protected abstract void initData();

    protected abstract void setListener();

    @Override
    public void reqStart(APIInterface apiInterface) {
        if (apiInterface instanceof FastWBApi) {
            FastWBApi api = (FastWBApi) apiInterface;
            if (api.getIsShowLoaddingDialog()) {
                showLoading();
            }
        }
    }

    @Override
    public void reqFailed(APIInterface apiInterface, String failedMessage) {

    }

    @Override
    public void reqSuccess(APIInterface apiInterface, String response) {

    }

    @Override
    public void reqFinish(APIInterface apiInterface) {
        if (apiInterface instanceof FastWBApi) {
            FastWBApi api = (FastWBApi) apiInterface;
            if (api.getIsShowLoaddingDialog()) {
                dismissLoading();
            }
        }
    }
}
