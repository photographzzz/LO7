package com.photograph.lo7.httpsender;

import rxhttp.wrapper.annotation.DefaultDomain;
import rxhttp.wrapper.annotation.Domain;

public class Url {
    public static String baseUrl = "http://10.0.2.2:8083/";

    @DefaultDomain //设置为默认域名
    public static String realPhone = "http://192.168.0.102:8083/";
    @Domain
    public static String url1 = "11";
}
