package com.hkllzh.fastweib.net.api;

import com.hkllzh.android.net.RequestParams;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * statuses/repost_timeline
 * <pre>
 * 获取指定微博的转发微博列表
 * 网页地址 http://open.weibo.com/wiki/2/statuses/repost_timeline
 * URL https://api.weibo.com/2/statuses/repost_timeline.json
 * 支持格式 JSON
 * HTTP请求方式 GET
 * 是否需要登录 是
 * 关于登录授权，参见 如何登录授权
 * 访问授权限制
 * 访问级别：普通接口
 * 频次限制：是
 * 关于频次限制，参见 接口访问权限说明
 * 请求参数
 * 必选	类型及范围	说明
 * source	false	string	采用OAuth授权方式不需要此参数，其他授权方式为必填参数，数值为应用的AppKey。
 * access_token	false	string	采用OAuth授权方式为必填参数，其他授权方式不需要此参数，OAuth授权后获得。
 * id	true	int64	需要查询的微博ID。
 * since_id	false	int64	若指定此参数，则返回ID比since_id大的微博（即比since_id时间晚的微博），默认为0。
 * max_id	false	int64	若指定此参数，则返回ID小于或等于max_id的微博，默认为0。
 * count	false	int	单页返回的记录条数，最大不超过200，默认为20。
 * page	false	int	返回结果的页码，默认为1。
 * filter_by_author	false	int	作者筛选类型，0：全部、1：我关注的人、2：陌生人，默认为0。
 * </pre>
 * <p/>
 * lizheng -- 2015/09/30
 */
public class StatusesRepost_timeline extends FastWBApi {
    private String mId;

    public StatusesRepost_timeline(Oauth2AccessToken accessToken, String id) {
        super(accessToken);
        mId = id;
    }

    @Override
    public String requestURL() {
        return "https://api.weibo.com/2/statuses/repost_timeline.json";
    }

    @Override
    public RequestMethod requestMethod() {
        return RequestMethod.GET;
    }

    @Override
    public RequestParams requestParams() {
        RequestParams params = new RequestParams();
        params.put("access_token", mAccessToken.getToken());
        params.put("id", mId);
        return params;
    }
}
