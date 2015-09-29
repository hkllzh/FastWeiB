package com.hkllzh.fastweib.ui;

import android.webkit.WebView;

import com.hkllzh.fastweib.FWBBaseActivity;
import com.hkllzh.fastweib.R;

/**
 * 测试网页显示
 * <p/>
 * lizheng -- 15/3/19
 * <p/>
 * FastWeiB
 */
public class TestWebViewActivity extends FWBBaseActivity {

    WebView webview;

    @Override
    public int getContentViewId() {
        return R.layout.ac_test_webview;
    }

    @Override
    protected void initView() {
        webview = (WebView) findViewById(R.id.webview);
    }

    @Override
    protected void initData() {

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setSupportZoom(false);

        webview.loadUrl("http://www.kxxf.net.cn//index.php/fhjdTask/record?userId=27&type=1&classes=&time=1426745351&encry_time=70f31487f9e524e464e250828cce9eac");
    }

    @Override
    protected void setListener() {

    }
}
