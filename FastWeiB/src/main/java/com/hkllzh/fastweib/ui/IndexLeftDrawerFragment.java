package com.hkllzh.fastweib.ui;

import android.widget.TextView;

import com.hkllzh.android.net.ResponseHandler;
import com.hkllzh.fastweib.BaseFragment;
import com.hkllzh.fastweib.R;
import com.hkllzh.fastweib.net.api.UsersShowApi;

/**
 * 首页左边导航部分
 * <p/>
 * lizheng -- 15/1/27
 * <p/>
 * FastWeiB
 */
public class IndexLeftDrawerFragment extends BaseFragment {

    private TextView tvTest;

    @Override
    public int getContentViewId() {
        return R.layout.fr_index_left_drawer;
    }

    @Override
    protected void initView() {
        tvTest = (TextView) getView().findViewById(R.id.tvTest);
    }

    @Override
    protected void initData() {

        new UsersShowApi(mAccessToken).execute(new ResponseHandler() {
            @Override
            public void failed(String errorInfo) {

            }

            @Override
            public void success(String success) {
                // tvTest.setText(success);
            }
        });

//        netRequest.get(WeiBoApi.UsersShow(mAccessToken), new RequestHandler() {
//            @Override
//            public void start() {
//                showLoading();
//            }
//
//            @Override
//            public void success(String response) {
//                tvTest.setText(response);
//            }
//
//            @Override
//            public void failure(String responseBody, Throwable error) {
//
//            }
//
//            @Override
//            public void finish() {
//                dismissLoading();
//            }
//        });
    }

    @Override
    protected void setListener() {

    }
}
