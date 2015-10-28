package com.hkllzh.fastweib.ui

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.google.gson.Gson
import com.hkllzh.android.net.APIInterface
import com.hkllzh.android.ui.BaseActivity
import com.hkllzh.fastweib.FWBBaseActivity
import com.hkllzh.fastweib.R
import com.hkllzh.fastweib.adapter.WBListAdapter
import com.hkllzh.fastweib.bean.HomeTimelineBean
import com.hkllzh.fastweib.net.FastWBRequest
import com.hkllzh.fastweib.net.api.StatusesUser_timelineApi

/**
 * 我的微博列表
 * <p>
 * lizheng -- 2015/10/23
 *
 */
public class MyWeiBActivity : FWBBaseActivity() {
    private var toolBar: Toolbar? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var recyclerViewWbList: RecyclerView? = null

    private var wbListAdapter: WBListAdapter?=null

    override fun getContentViewId(): Int {
        return R.layout.ac_my_weib
    }

    override fun initView() {
        toolBar = findViewById(R.id.toolbar) as Toolbar?
        toolBar?.title = "我的微博"
        setSupportActionBar(toolBar)

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout) as SwipeRefreshLayout
        swipeRefreshLayout?.setColorSchemeResources(R.color.refresh_color_1, R.color.refresh_color_2, R.color.refresh_color_3, R.color.refresh_color_4)

        recyclerViewWbList = findViewById(R.id.recyclerViewWbList)as RecyclerView
        recyclerViewWbList?.layoutManager = LinearLayoutManager(this)

    }

    override fun initData() {
        wbListAdapter = WBListAdapter()
        recyclerViewWbList?.adapter = wbListAdapter

        val api = StatusesUser_timelineApi(mAccessToken)
        api.isShowLoaddingDialog = true
        FastWBRequest.getInstance().execute(api, this)
    }

    override fun setListener() {
    }

    override fun reqSuccess(apiInterface: APIInterface?, response: String?) {
        BaseActivity.log.e(makeLogTag(MyWeiBActivity::class.java), "response:" + response)
        val bean = Gson().fromJson(response, HomeTimelineBean::class.java)
        wbListAdapter?.setData(bean.statuses)
    }
}