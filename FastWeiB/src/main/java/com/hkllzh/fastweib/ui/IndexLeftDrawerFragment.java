package com.hkllzh.fastweib.ui;

import android.widget.TextView;

import com.hkllzh.android.net.ResponseInterface;
import com.hkllzh.android.net.okhttp.OkHttpResponse;
import com.hkllzh.fastweib.FWBBaseFragment;
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
public class IndexLeftDrawerFragment extends FWBBaseFragment {

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


    }

    @Override
    protected void setListener() {

    }
}
