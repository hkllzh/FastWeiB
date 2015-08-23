//package com.hkllzh.fastweib.net;
//
//import android.text.TextUtils;
//
//import com.loopj.android.http.RequestParams;
//import com.sina.weibo.sdk.auth.Oauth2AccessToken;
//
///**
// * 微博数据接口常量类
// * <p/>
// * lizheng -- 15/1/23
// * <p/>
// * FastWeiB
// */
//public final class WeiBoApi {
//    private WeiBoApi() {
//    }
//
//    /**
//     * 获取用户信息
//     * <pre>
//     * 网页地址：http://open.weibo.com/wiki/2/users/show
//     * 接口地址：https://api.weibo.com/2/users/show.json
//     * 请求方式：GET
//     * 参数：
//     * source       false	string	采用OAuth授权方式不需要此参数，其他授权方式为必填参数，数值为应用的AppKey。
//     * access_token false	string	采用OAuth授权方式为必填参数，其他授权方式不需要此参数，OAuth授权后获得。
//     * uid          false	int64	需要查询的用户ID。
//     * screen_name  false	string	需要查询的用户昵称。
//     * </pre>
//     *
//     * @return 地址&参数
//     */
//    public static UrlParamsBean UsersShow(Oauth2AccessToken accessToken) {
//        RequestParams params = new RequestParams();
//        params.add("access_token", accessToken.getToken());
//        params.add("uid", accessToken.getUid());
//        return new UrlParamsBean("https://api.weibo.com/2/users/show.json", params);
//    }
//
//    /**
//     * 获取当前登录用户及其所关注用户的最新微博
//     * <pre>
//     * 网页地址：http://open.weibo.com/wiki/2/statuses/home_timeline
//     * 接口地址：https://api.weibo.com/2/statuses/home_timeline.json
//     * 请求方式：GET
//     * 请求参数
//     * 必选	类型及范围	说明
//     * source	    false	string	采用OAuth授权方式不需要此参数，其他授权方式为必填参数，数值为应用的AppKey。
//     * access_token	false	string	采用OAuth授权方式为必填参数，其他授权方式不需要此参数，OAuth授权后获得。
//     * since_id	    false	int64	若指定此参数，则返回ID比since_id大的微博（即比since_id时间晚的微博），默认为0。
//     * max_id	    false	int64	若指定此参数，则返回ID小于或等于max_id的微博，默认为0。
//     * count	    false	int	单页返回的记录条数，最大不超过100，默认为20。
//     * page	        false	int	返回结果的页码，默认为1。
//     * base_app	    false	int	是否只获取当前应用的数据。0为否（所有数据），1为是（仅当前应用），默认为0。
//     * feature	    false	int	过滤类型ID，0：全部、1：原创、2：图片、3：视频、4：音乐，默认为0。
//     * trim_user	false	int	返回值中user字段开关，0：返回完整user字段、1：user字段仅返回user_id，默认为0。*
//     * </pre>
//     *
//     *  since_id,比这个新的。
//     *
//     * @return 地址&参数
//     */
//    public static UrlParamsBean StatusesHome_timeline(Oauth2AccessToken accessToken, String max_id) {
//        RequestParams params = new RequestParams();
//        params.add("access_token", accessToken.getToken());
//        params.add("max_id", TextUtils.isEmpty(max_id) ? "0" : max_id);
//        return new UrlParamsBean("https://api.weibo.com/2/statuses/home_timeline.json", params);
//    }
//}
