package com.hkllzh.fastweib.ui

import android.widget.TextView

import com.hkllzh.fastweib.BaseFragment
import com.hkllzh.fastweib.R

/**
 * 评论页面
 *
 *
 * lizheng -- 2015/09/30
 */
public class ReviewsFragment : BaseFragment() {

    private var tvTest: TextView? = null

    override fun getContentViewId(): Int {
        return R.layout.fr_reviews_page
    }

    override fun initView() {
        tvTest = findViewById<TextView>(R.id.tvTest)
    }

    override fun initData() {
        val sb = StringBuffer()
        for (i in 0..999) {
            sb.append("lizheng").append(i)
        }
        tvTest!!.text = sb.toString()
        
    }

    override fun setListener() {

    }
}
