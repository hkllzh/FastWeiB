package com.hkllzh.fastweib.ui

import android.widget.TextView
import com.hkllzh.fastweib.FWBBaseActivity
import com.hkllzh.fastweib.R

/**
 * 我的微博列表
 * <p>
 * lizheng -- 2015/10/23
 *
 */
public class MyWeiBActivity : FWBBaseActivity() {

    private var tvTest: TextView? = null

    override fun getContentViewId(): Int {
        return R.layout.ac_my_weib
    }

    override fun initView() {
        tvTest = findViewById(R.id.tvTest) as TextView?
    }

    override fun initData() {
        tvTest?.text = "我的微博列表"
    }

    override fun setListener() {
    }

}