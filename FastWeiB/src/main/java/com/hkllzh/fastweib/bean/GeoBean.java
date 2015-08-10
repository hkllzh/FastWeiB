package com.hkllzh.fastweib.bean;

/**
 * 地理信息数据类
 * <p/>
 * 网页地址：http://open.weibo.com/wiki/常见返回对象数据结构#.E5.9C.B0.E7.90.86.E4.BF.A1.E6.81.AF.EF.BC.88geo.EF.BC.89
 * <p/>
 * <pre>
 * 返回值字段	字段类型	字段说明
 * longitude	    string	经度坐标
 * latitude	        string	维度坐标
 * city	            string	所在城市的城市代码
 * province	        string	所在省份的省份代码
 * city_name	    string	所在城市的城市名称
 * province_name	string	所在省份的省份名称
 * address	        string	所在的实际地址，可以为空
 * pinyin	        string	地址的汉语拼音，不是所有情况都会返回该字段
 * more	            string	更多信息，不是所有情况都会返回该字段
 * </pre>
 * <p/>
 * * * *
 * lizheng -- 15/1/28
 * <p/>
 * FastWeiB
 */
public class GeoBean {
    public String longitude;
    public String latitude;
    public String city;
    public String province;
    public String city_name;
    public String province_name;
    public String address;
    public String pinyin;
    public String more;
}
