package com.hkllzh.fastweib;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.hkllzh.fastweib.net.NetRequest;
import com.hkllzh.fastweib.util.SPUtil;


/**
 * 本项目Activity的基类
 * <p/>
 * lizheng -- 15/1/10
 * <p/>
 * com.hkllzh.fastweib
 */
public class BaseActivity extends FragmentActivity {

    protected NetRequest netRequest;

    protected static final SPUtil spUtil;
    protected static final int W_PX;
    protected static final int H_PX;

    static {
        spUtil = SPUtil.getInstance();
        W_PX = spUtil.getInt(C.SP.SCREEN_WIDTH, 0);
        H_PX = spUtil.getInt(C.SP.SCREEN_HEIGHT, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        netRequest = new NetRequest(this);
    }
}
