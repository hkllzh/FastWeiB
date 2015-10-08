package com.hkllzh.fastweib.ui

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import com.hkllzh.android.ui.BaseActivity
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

    private var id = ""

    override fun loadIntentData() {
        id = intent.getStringExtra("id")
    }

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


        val fragments = initFragmentPages()

        adapter = SingleWeiBoViewPagerAdapter(supportFragmentManager)
        adapter?.setPages(fragments.toArrayList())
        viewpager?.adapter = adapter

        tabLayout?.setupWithViewPager(viewpager)
    }

    private fun initFragmentPages(): List<ReviewsFragment> {
        var reviewsFragment = ReviewsFragment()
        var bu: Bundle = Bundle()
        bu.putString("id", id)
        reviewsFragment.arguments = bu

        var reviewsFragment2 = ReviewsFragment()
        var bu2: Bundle = Bundle()
        bu2.putString("id", id)
        reviewsFragment2.arguments = bu

        var reviewsFragment3 = ReviewsFragment()
        var bu3: Bundle = Bundle()
        bu3.putString("id", id)
        reviewsFragment3.arguments = bu

        return listOf(reviewsFragment, reviewsFragment2, reviewsFragment3)
    }

    override fun setListener() {
    }
}