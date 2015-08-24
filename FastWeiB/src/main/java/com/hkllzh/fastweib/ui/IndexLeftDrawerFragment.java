package com.hkllzh.fastweib.ui;

import android.widget.TextView;

import com.hkllzh.android.net.ResponseInterface;
import com.hkllzh.android.net.okhttp.OkHttpResponse;
import com.hkllzh.fastweib.BaseFragment;
import com.hkllzh.fastweib.R;
import com.hkllzh.fastweib.net.FastWBRequest;
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

        FastWBRequest.getInstance().execute(new UsersShowApi(mAccessToken), new OkHttpResponse() {
            @Override
            public void start() {

            }

            @Override
            public void failed(String errorInfo) {

            }

            @Override
            public void success(final String response) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvTest.setText(response);
                    }
                });
            }

            @Override
            public void finish() {

            }
        });
    }

    @Override
    protected void setListener() {

    }
}
