package com.hkllzh.fastweib.ui;

import android.widget.TextView;

import com.hkllzh.fastweib.BaseActivity;
import com.hkllzh.fastweib.R;
import com.hkllzh.fastweib.net.RequestHandler;
import com.hkllzh.fastweib.net.WeiBoApi;

/**
 * 项目首页
 * <p/>
 *
 * @author lizheng -- li396858359@163.com
 */
public class IndexActivity extends BaseActivity {

    private TextView tvTest;

    @Override
    public int getContentViewId() {
        return R.layout.ac_index;
    }

    @Override
    protected void initView() {
        tvTest = (TextView) findViewById(R.id.tvTest);
    }

    @Override
    protected void initData() {

        netRequest.get(WeiBoApi.UsersShow(mAccessToken), new RequestHandler() {
            @Override
            public void start() {
                showLoading();
            }

            @Override
            public void success(String response) {
                tvTest.setText(response);
            }

            @Override
            public void failure(String responseBody, Throwable error) {

            }

            @Override
            public void finish() {
                dismissLoading();
            }
        });

    }

    @Override
    protected void setListener() {

    }
}
