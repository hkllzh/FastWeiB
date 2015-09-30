package com.hkllzh.fastweib;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * {@link android.support.v7.widget.RecyclerView.Adapter}的一个基础实现
 * <br/>
 * 增加了加载更多和设置{@link java.util.ArrayList}数据功能
 * <p/>
 * lizheng -- 15/1/30
 * <p/>
 * FastWeiB
 */
public abstract class BaseRVAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter {

    protected ArrayList<T> mData;

    public void setLoadMore(LoadMore loadMore) {
        this.loadMore = loadMore;
    }

    private LoadMore loadMore;

    public void setData(ArrayList<T> mData) {
        this.mData = mData;
        this.notifyDataSetChanged();
    }

    public void addMoreData(ArrayList<T> data) {
        if (null == this.mData) {
            setData(data);
        } else {
            this.mData.addAll(data);
            this.notifyDataSetChanged();
        }
    }

    protected View getView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(getItemLayoutRes(viewType), parent, false);
    }

    protected abstract int getItemLayoutRes(int viewType);


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        baseOnBindViewHolder((VH) holder, mData.get(position));
        if (getItemCount() > 1 && getItemCount() - 2 == position) {
            if (null != loadMore) {
                loadMore.loadMore();
            }
        }
    }

    @Override
    public int getItemCount() {
        if (null == mData) {
            return 0;
        }
        return mData.size();
    }

    public abstract void baseOnBindViewHolder(VH holder, T bean);


    public interface LoadMore {
        void loadMore();
    }

    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        this.mOnItemClickListener = listener;
    }

    protected OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener<T> {
        void onItemClick(T bean);
    }
}
