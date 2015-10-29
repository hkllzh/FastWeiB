package com.hkllzh.fastweib.ui

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.hkllzh.android.net.APIInterface
import com.hkllzh.android.ui.BaseFragment
import com.hkllzh.android.util.log.LogHandler
import com.hkllzh.fastweib.FWBBaseFragment
import com.hkllzh.fastweib.R
import com.hkllzh.fastweib.adapter.SingleWeiBoInfoAdapter
import com.hkllzh.fastweib.net.FastWBRequest
import com.hkllzh.fastweib.net.api.StatusesRepost_timeline

/**
 * 评论页面
 *
 * lizheng -- 2015/09/30
 */
public class ReviewsFragment : FWBBaseFragment() {

    private var recyclerViewReviewsPageList: RecyclerView? = null
    private var adapter: SingleWeiBoInfoAdapter? = null

    override fun getContentViewId(): Int {
        return R.layout.fr_reviews_page
    }

    override fun initView() {
        recyclerViewReviewsPageList = findViewById(R.id.recyclerViewReviewsPageList)
    }

    override fun initData() {
        recyclerViewReviewsPageList!!.layoutManager = LinearLayoutManager(activity)
        adapter = SingleWeiBoInfoAdapter()

        recyclerViewReviewsPageList!!.adapter = adapter

        FastWBRequest.getInstance().execute(StatusesRepost_timeline(mAccessToken, arguments.getString("id")), this)
    }

    override fun setListener() {

    }

    override fun reqSuccess(apiInterface: APIInterface?, response: String?) {
        BaseFragment.log.e(LogHandler.makeLogTag(ReviewsFragment::class.java), "response:" + response)
    }
}
