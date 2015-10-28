package com.hkllzh.fastweib.net.api

import com.hkllzh.android.net.APIInterface
import com.hkllzh.android.net.RequestParams
import com.hkllzh.fastweib.net.FastWBApi
import com.sina.weibo.sdk.auth.Oauth2AccessToken

/**
 *
 * 网址：http://open.weibo.com/wiki/2/statuses/user_timeline
statuses/user_timeline
获取某个用户最新发表的微博列表
URL
https://api.weibo.com/2/statuses/user_timeline.json
支持格式
JSON
HTTP请求方式
GET
是否需要登录
是
关于登录授权，参见 如何登录授权
访问授权限制
访问级别：普通接口
频次限制：是
关于频次限制，参见 接口访问权限说明
请求参数
必选	类型及范围	说明
source	false	string	采用OAuth授权方式不需要此参数，其他授权方式为必填参数，数值为应用的AppKey。
access_token	false	string	采用OAuth授权方式为必填参数，其他授权方式不需要此参数，OAuth授权后获得。
uid	false	int64	需要查询的用户ID。
screen_name	false	string	需要查询的用户昵称。
since_id	false	int64	若指定此参数，则返回ID比since_id大的微博（即比since_id时间晚的微博），默认为0。
max_id	false	int64	若指定此参数，则返回ID小于或等于max_id的微博，默认为0。
count	false	int	单页返回的记录条数，最大不超过100，超过100以100处理，默认为20。
page	false	int	返回结果的页码，默认为1。
base_app	false	int	是否只获取当前应用的数据。0为否（所有数据），1为是（仅当前应用），默认为0。
feature	false	int	过滤类型ID，0：全部、1：原创、2：图片、3：视频、4：音乐，默认为0。
trim_user	false	int	返回值中user字段开关，0：返回完整user字段、1：user字段仅返回user_id，默认为0。
注意事项
获取自己的微博，参数uid与screen_name可以不填，则自动获取当前登录用户的微博；
指定获取他人的微博，参数uid与screen_name二者必选其一，且只能选其一；
接口升级后：uid与screen_name只能为当前授权用户；
读取当前授权用户所有关注人最新微博列表，请使用：获取当前授权用户及其所关注用户的最新微博接口（statuses/home_timeline）；
此接口最多只返回最新的5条数据，官方移动SDK调用可返回10条；
 * <p>
 * lizheng -- 2015/10/27
 *
 */
class StatusesUser_timelineApi(accessToken: Oauth2AccessToken) : FastWBApi(accessToken) {

    override fun requestURL(): String? {
        return "https://api.weibo.com/2/statuses/user_timeline.json"
    }

    override fun requestMethod(): APIInterface.RequestMethod? {
        return APIInterface.RequestMethod.GET
    }

    override fun requestParams(): RequestParams? {
        val p = RequestParams()
        p.put("access_token",mAccessToken?.token)
        p.put("uid",mAccessToken?.uid)
        p.put("trim_user","0")
        return p
    }
}