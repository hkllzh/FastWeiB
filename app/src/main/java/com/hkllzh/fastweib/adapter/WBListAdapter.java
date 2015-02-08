package com.hkllzh.fastweib.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hkllzh.fastweib.BaseRVAdapter;
import com.hkllzh.fastweib.R;
import com.hkllzh.fastweib.bean.StatusBean;
import com.hkllzh.fastweib.util.WBTimeParseUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.joda.time.DateTime;

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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_item_wb_list, parent, false);
        return new WBListViewHolder(v);
    }

    @Override
    public void baseOnBindViewHolder(WBListViewHolder holder, int position) {
        ImageLoader.getInstance().displayImage(mData.get(position).user.avatar_hd, holder.imavHeadPortrait);
        holder.tvName.setText(mData.get(position).user.screen_name);
        holder.tvTime.setText(time2Show(WBTimeParseUtil.parse(mData.get(position).created_at)));
        holder.tvContent.setText(mData.get(position).text);

        if (null != mData.get(position).retweeted_status) {
            holder.cardViewRetweeted.setVisibility(View.VISIBLE);
            holder.tvRetweetedStatus.setText("@" + mData.get(position).retweeted_status.user.screen_name + " " + mData.get(position).retweeted_status.text);
        } else {
            holder.cardViewRetweeted.setVisibility(View.GONE);
        }
    }

    class WBListViewHolder extends RecyclerView.ViewHolder {

        public ImageView imavHeadPortrait;
        public TextView tvName;
        public TextView tvTime;
        public TextView tvContent;
        public TextView tvRetweetedStatus;
        public CardView cardViewRetweeted;

        public WBListViewHolder(View itemView) {
            super(itemView);
            imavHeadPortrait = (ImageView) itemView.findViewById(R.id.imavHeadPortrait);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            tvRetweetedStatus = (TextView) itemView.findViewById(R.id.tvRetweetedStatus);
            cardViewRetweeted = (CardView) itemView.findViewById(R.id.cardViewRetweeted);
        }
    }

    /**
     * 时间转换为友好的显示格式
     *
     * @param dateTime 需要显示的格式
     * @return 友好的显示格式
     */
    private static String time2Show(DateTime dateTime) {
        /**
         * 1分钟以下，显示xx秒前 
         * 1小时以下，显示xx分钟前
         * 1天以下，显示xx小时前 
         */

        String temp = "";
        // isAfterNow 在当前以后，即大于当前
        if (dateTime.plusSeconds(30).isAfterNow()) {
            temp = "刚刚";//String.valueOf((DateTime.now().getMillis() - dateTime.getMillis()) / 1000) + "秒前";
        }else if (dateTime.plusMinutes(1).isAfterNow()) {
            temp = String.valueOf((DateTime.now().getMillis() - dateTime.getMillis()) / 1000) + "秒前";
        } else if (dateTime.plusHours(1).isAfterNow()) {
            temp = String.valueOf((DateTime.now().getMillis() - dateTime.getMillis()) / 1000 / 60) + "分钟前";
        } else if (dateTime.plusDays(1).isAfterNow()) {
            temp = String.valueOf((DateTime.now().getMillis() - dateTime.getMillis()) / 1000 / 60 / 60) + "小时前";
        }else{
            temp = dateTime.toString("MM-dd HH:mm");
        }

        return temp;//+ dateTime.toString("HH:mm:ss");
    }

}
