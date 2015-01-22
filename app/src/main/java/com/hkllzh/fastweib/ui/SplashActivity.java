package com.hkllzh.fastweib.ui;

import android.content.Intent;
import android.os.Handler;

import com.hkllzh.fastweib.BaseActivity;
import com.hkllzh.fastweib.R;

/**
 * 启动页面
 * <p/>
 * lizheng -- 15/1/11
 * <p/>
 * FastWeiB
 */
public class SplashActivity extends BaseActivity {

    @Override
    public int getContentViewId() {
        return R.layout.ac_splash;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(mActivity, IndexActivity.class));
            }
        }, 500);
    }

    @Override
    protected void setListener() {

    }
}
