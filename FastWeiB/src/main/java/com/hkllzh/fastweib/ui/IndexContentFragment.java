package com.hkllzh.fastweib.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.hkllzh.android.net.APIInterface;
import com.hkllzh.fastweib.BaseRVAdapter;
import com.hkllzh.fastweib.FWBBaseFragment;
import com.hkllzh.fastweib.R;
import com.hkllzh.fastweib.adapter.WBListAdapter;
import com.hkllzh.fastweib.bean.HomeTimelineBean;
import com.hkllzh.fastweib.bean.StatusBean;
import com.hkllzh.fastweib.net.FastWBRequest;
import com.hkllzh.fastweib.net.api.StatusesHome_timelineApi;

/**
 * 首页的内容部分
 * <p/>
 * lizheng -- 15/1/27
 * <p/>
 * FastWeiB
 */
public class IndexContentFragment extends FWBBaseFragment {

    private static final String TAG = "* IndexContentFragment * ";
    private static final boolean isShowLog = true;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerViewWbList;
    private WBListAdapter wbListAdapter;


    private String mMax_id;

    public IndexActivity parentActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IndexActivity) {
            parentActivity = (IndexActivity) context;
        }

    }

    @Override
    public int getContentViewId() {
        return R.layout.fr_index_content;
    }

    @Override
    protected void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipeRefreshLayout);
        recyclerViewWbList = (RecyclerView) getView().findViewById(R.id.recyclerViewWbList);
        swipeRefreshLayout.setColorSchemeResources(R.color.refresh_color_1, R.color.refresh_color_2,
                R.color.refresh_color_3, R.color.refresh_color_4);

    }

    @Override
    protected void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        wbListAdapter = new WBListAdapter();
        recyclerViewWbList.setLayoutManager(layoutManager);
        recyclerViewWbList.setAdapter(wbListAdapter);

        requestData("");
    }

    private void requestData(final String max_id) {
        FastWBRequest.getInstance().execute(new StatusesHome_timelineApi(mAccessToken, max_id), this);
    }

    @Override
    protected void setListener() {

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestData("");
            }
        });
        wbListAdapter.setLoadMore(new BaseRVAdapter.LoadMore() {
            @Override
            public void loadMore() {
                requestData(mMax_id);
            }
        });

        wbListAdapter.setOnItemClickListener(new BaseRVAdapter.OnItemClickListener<StatusBean>() {
            @Override
            public void onItemClick(StatusBean bean) {
                startActivity(new Intent(getActivity(), SingleWeiboActivity.class).putExtra("id", bean.id));
            }
        });
    }

    public void actionMenuRefresh() {
        requestData("");
    }

    @Override
    public void reqStart(APIInterface apiInterface) {
        super.reqStart(apiInterface);
        StatusesHome_timelineApi api = (StatusesHome_timelineApi) apiInterface;
        if (TextUtils.isEmpty(api.mMaxId)) {
            showLoading();
        }
    }

    @Override
    public void reqSuccess(APIInterface apiInterface, String response) {
        super.reqSuccess(apiInterface, response);
        StatusesHome_timelineApi api = (StatusesHome_timelineApi) apiInterface;
        HomeTimelineBean bean = new Gson().fromJson(response, HomeTimelineBean.class);
        if (TextUtils.isEmpty(api.mMaxId)) {
            wbListAdapter.setData(bean.statuses);

            if (recyclerViewWbList.getChildCount() > 0 && recyclerViewWbList.getLayoutManager().getPosition(recyclerViewWbList.getChildAt(0)) > 5) {
                recyclerViewWbList.scrollToPosition(4);
            }

            recyclerViewWbList.smoothScrollToPosition(0);
        } else {
            wbListAdapter.addMoreData(bean.statuses);
        }
        mMax_id = bean.max_id;
    }

    @Override
    public void reqFinish(APIInterface apiInterface) {
        super.reqFinish(apiInterface);
        dismissLoading();
        swipeRefreshLayout.setRefreshing(false);
    }
}
