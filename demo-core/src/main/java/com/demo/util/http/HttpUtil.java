package com.demo.util.http;

import com.alibaba.fastjson.JSON;
import com.demo.util.string.StringByteUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Desc:
 * User: sunjz
 * Email: jiuzeng.sun@leappmusic.com
 * Date: 2017/7/15
 * Time: 下午1:55
 * To change this template use File | Settings | File Templates.
 */
public class HttpUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    private static final int socketTimeout = 1000;
    private static final int connectionTimeout = 1000;

    public static String get(String url) throws Exception {
        LOGGER.debug(String.format("http get url is %s", url));
        if (StringUtils.isBlank(url)) {
            LOGGER.error(String.format("get params is null, url is %s", url));
            return StringByteUtil.EMPTY_STRING;
        }

        // 创建HttpClient对象
        HttpClient client = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectionTimeout).build();

        // 创建GET请求（在构造器中传入URL字符串即可）
        HttpGet get = new HttpGet(url);
        get.setConfig(requestConfig);

        // 调用HttpClient对象的execute方法获得响应
        HttpResponse response = client.execute(get);

        // 调用HttpResponse对象的getEntity方法得到响应实体
        HttpEntity httpEntity = response.getEntity();

        // 使用EntityUtils工具类得到响应的字符串表示
        String result = EntityUtils.toString(httpEntity, StringByteUtil.DEFAULT_CHARSET);

        LOGGER.info(String.format("HttpUtil get result %s", result));
        return result;
    }

    public static String post(String url, Map<String, String> params) throws Exception {
        if (StringUtils.isBlank(url) || MapUtils.isEmpty(params)) {
            LOGGER.error(String.format("post params is null, url is %s, params is %s", url, JSON.toJSONString(params)));
            return StringByteUtil.EMPTY_STRING;
        }
        // 创建HttpClient对象
        HttpClient client = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectionTimeout).build();

        // 创建POST请求
        HttpPost post = new HttpPost(url);
        post.setConfig(requestConfig);

        // 创建一个List容器，用于存放基本键值对（基本键值对即：参数名-参数值）
        List<BasicNameValuePair> parameters = new ArrayList<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        // 向POST请求中添加消息实体
        post.setEntity(new UrlEncodedFormEntity(parameters, StringByteUtil.DEFAULT_CHARSET));

        // 得到响应并转化成字符串
        HttpResponse response = client.execute(post);
        HttpEntity httpEntity = response.getEntity();
        String result = EntityUtils.toString(httpEntity, StringByteUtil.DEFAULT_CHARSET);

        LOGGER.debug(String.format("HttpUtil post result %s", result));
        return result;
    }

}
