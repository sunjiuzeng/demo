package com.demo.util.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Desc:
 * User: sunjz
 * Email: jiuzeng.sun@leappmusic.com
 * Date: 2017/8/23
 * Time: 上午11:08
 * To change this template use File | Settings | File Templates.
 */
public class JsonUtil {

    static {

    }

    public static <T> T parseObject(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json) || clazz == null) {
            return null;
        }

        return JSON.parseObject(json, clazz);
    }

    public static <K, V> Map<K, V> parseToMap(String json, Class<K> keyType, Class<V> valueType) {
        if (StringUtils.isBlank(json) || keyType == null || valueType == null) {
            return Collections.emptyMap();
        }

        return JSON.parseObject(json, new TypeReference<Map<K, V>>(keyType, valueType) {
        });
    }

    public static <T> List<T> parseToArray(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

}
