package com.hkllzh.fastweib.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hkllzh.fastweib.BaseRVAdapter

/**
 * 单条微博页面的适配器
 * <p>
 * lizheng -- 2015/09/30
 *
 */
public class SingleWeiBoInfoAdapter : BaseRVAdapter<SingleWeiBoInfoAdapter.SingleWeiBoInfoViewHolder, String>() {

    override fun getItemLayoutRes(viewType: Int): Int {
        throw UnsupportedOperationException()
    }

    override fun baseOnBindViewHolder(holder: SingleWeiBoInfoViewHolder?, bean: String?) {
        throw UnsupportedOperationException()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        throw UnsupportedOperationException()
    }

    inner class SingleWeiBoInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}