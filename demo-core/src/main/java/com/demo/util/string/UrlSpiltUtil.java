package com.demo.util.string;

import org.apache.commons.lang3.StringUtils;

import java.net.URI;

/**
 * Desc:
 * User: sunjz
 * Email: jiuzeng.sun@leappmusic.com
 * Date: 2017/8/14
 * Time: 上午11:57
 * To change this template use File | Settings | File Templates.
 */
public class UrlSpiltUtil {

    public static String getUriByUrl(String url) {
        if (StringUtils.isBlank(url)) {
            return StringByteUtil.EMPTY_STRING;
        }

        URI uri = URI.create(url);
        return uri.getPath();
    }
}
