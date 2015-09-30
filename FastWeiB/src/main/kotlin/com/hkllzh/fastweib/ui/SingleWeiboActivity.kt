package com.hkllzh.fastweib.ui

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import com.hkllzh.fastweib.FWBBaseActivity
import com.hkllzh.fastweib.R
import com.hkllzh.fastweib.adapter.SingleWeiBoViewPagerAdapter

/**
 * 单条微博信息页面
 * <p>
 * lizheng -- 2015/09/29
 *
 */
public class SingleWeiboActivity : FWBBaseActivity() {

    private var toolbar: Toolbar? = null
    private var tabLayout: TabLayout? = null
    private var viewpager: ViewPager? = null

    private var adapter: SingleWeiBoViewPagerAdapter? = null

    override fun getContentViewId(): Int {
        return R.layout.ac_single_weibo;
    }

    override fun initView() {
        toolbar = findViewById(R.id.toolbar) as Toolbar
        tabLayout = findViewById(R.id.tabLayout) as TabLayout
        viewpager = findViewById(R.id.viewpager) as ViewPager
    }

    override fun initData() {
        toolbar?.title = "正文"
        setSupportActionBar(toolbar)

        adapter = SingleWeiBoViewPagerAdapter(supportFragmentManager)
        viewpager?.adapter = adapter

        tabLayout?.setupWithViewPager(viewpager)
    }

    override fun setListener() {
    }
}