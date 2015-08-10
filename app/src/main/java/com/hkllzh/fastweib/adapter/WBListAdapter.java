package com.hkllzh.fastweib.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hkllzh.fastweib.BaseRVAdapter;
import com.hkllzh.fastweib.R;
import com.hkllzh.fastweib.bean.StatusBean;
import com.hkllzh.fastweib.util.WBTimeUtil;
import com.hkllzh.fastweib.view.WBListImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 微博列表适配器
 * <p/>
 * lizheng -- 15/1/28
 * <p/>
 * FastWeiB
 */
public class WBListAdapter extends BaseRVAdapter<WBListAdapter.WBListViewHolder, StatusBean> {

    private static final String TAG = "* WBListAdapter * ";
    private static final boolean isShowLog = true;

    @Override
    public WBListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WBListViewHolder(getView(parent, viewType));
    }

    @Override
    protected int getItemLayoutRes(int viewType) {
        return R.layout.lv_item_wb_list;
    }

    @Override
    public void baseOnBindViewHolder(WBListViewHolder holder, StatusBean bean) {
        // ImageLoader.getInstance().displayImage(bean.user.avatar_hd, holder.imavHeadPortrait);
        holder.imavHeadPortrait.setImageURI(Uri.parse(bean.user.avatar_hd));
        holder.tvName.setText(bean.user.screen_name);
        holder.tvTime.setText(WBTimeUtil.time2Show(WBTimeUtil.parse(bean.created_at)));
        holder.tvContent.setText(bean.text);

        if (null != bean.retweeted_status) {
            holder.vLine.setVisibility(View.VISIBLE);
            holder.tvRetweetedStatus.setVisibility(View.VISIBLE);
            if (null != bean.retweeted_status.user) {
                holder.tvRetweetedStatus.setText("@" + bean.retweeted_status.user.screen_name + " " + bean.retweeted_status.text);
            } else {
                holder.tvRetweetedStatus.setText(bean.retweeted_status.text);
            }

            if (null != bean.retweeted_status.pic_urls && 0 != bean.retweeted_status.pic_urls.size()) {
                holder.wbImages.setVisibility(View.VISIBLE);
                holder.wbImages.setPic_urls(bean.retweeted_status.pic_urls);
            } else {
                holder.wbImages.setVisibility(View.GONE);
            }
        } else {
            holder.vLine.setVisibility(View.GONE);
            holder.tvRetweetedStatus.setVisibility(View.GONE);

            if (null != bean.pic_urls && 0 != bean.pic_urls.size()) {
                holder.wbImages.setVisibility(View.VISIBLE);
                holder.wbImages.setPic_urls(bean.pic_urls);
            } else {
                holder.wbImages.setVisibility(View.GONE);
            }
        }
    }

    class WBListViewHolder extends RecyclerView.ViewHolder {

        public SimpleDraweeView imavHeadPortrait;
        public TextView tvName;
        public TextView tvTime;
        public TextView tvContent;
        public TextView tvRetweetedStatus;
        public View vLine;
        public WBListImageView wbImages;

        public WBListViewHolder(View itemView) {
            super(itemView);
            imavHeadPortrait = (SimpleDraweeView) itemView.findViewById(R.id.imavHeadPortrait);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            tvRetweetedStatus = (TextView) itemView.findViewById(R.id.tvRetweetedStatus);
            vLine = itemView.findViewById(R.id.vLine);
            wbImages = (WBListImageView) itemView.findViewById(R.id.wbImages);
        }
    }

}
