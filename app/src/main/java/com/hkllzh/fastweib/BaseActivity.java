package com.hkllzh.fastweib;

import android.os.Bundle;

import com.google.gson.JsonObject;
import com.hkllzh.fastweib.net.NetRequest;
import com.hkllzh.fastweib.net.RequestHandler;

import roboguice.activity.RoboFragmentActivity;

/**
 * 本项目Activity的基类
 * <p/>
 * lizheng -- 15/1/10
 * <p/>
 * com.hkllzh.fastweib
 */
public class BaseActivity extends RoboFragmentActivity {

    protected NetRequest netRequest;
    // protected static final int W_PX;
    // protected static final int H_PX;

    static {
        // W_PX = SPManagement.getSPUtilInstance(C.SP.SP_NAME).getInt(C.SP.SCREEN_WIDTH, 0);
        // H_PX = SPManagement.getSPUtilInstance(C.SP.SP_NAME).getInt(C.SP.SCREEN_HEIGHT, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        netRequest = new NetRequest(this);
    }
}
