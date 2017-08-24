package com.demo.util.string;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;

/**
 * Desc:
 * User: sunjz
 * Email: jiuzeng.sun@leappmusic.com
 * Date: 2017/8/15
 * Time: 下午12:10
 * To change this template use File | Settings | File Templates.
 */
public class StringByteUtil {
    /**
     * The constant DEFAULT_ENCODING.
     */
    public static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * The constant DEFAULT_CHARSET.
     */
    public static final Charset DEFAULT_CHARSET = Charset.forName(DEFAULT_ENCODING);

    /**
     * The constant EMPTY_BYTES.
     */
    public static final byte[] EMPTY_BYTES = new byte[0];

    /**
     * The constant EMPTY_STRING.
     */
    public static final String EMPTY_STRING = "";

    /**
     * String convert charset charset.
     *
     * @param charset the charset
     * @return the charset
     */
    public static Charset stringConvertCharset(String charset) {
        Charset encodingCharset;
        try {
            encodingCharset = Charset.forName(charset);
            return encodingCharset;
        } catch (Exception e) {
            return Charset.defaultCharset();
        }
    }

    /**
     * Get bytes by string byte [ ].
     *
     * @param source  the source
     * @param charset the charset
     * @return the byte [ ]
     */
    public static byte[] getBytesByString(String source, Charset charset) {
        if (StringUtils.isBlank(source)) {
            return EMPTY_BYTES;
        }

        return source.getBytes(charset);
    }

    /**
     * Get bytes by string byte [ ].
     *
     * @param source  the source
     * @param charset the charset
     * @return the byte [ ]
     */
    public static byte[] getBytesByString(String source, String charset) {
        Charset encodingCharset = stringConvertCharset(charset);
        return getBytesByString(source, encodingCharset);
    }

    /**
     * Get bytes by string and default encoding byte [ ].
     *
     * @param source the source
     * @return the byte [ ]
     */
    public static byte[] getBytesByStringAndDefaultEncoding(String source) {
        return getBytesByString(source, DEFAULT_CHARSET);
    }

    /**
     * Gets string by bytes.
     *
     * @param sources the sources
     * @param charset the charset
     * @return the string by bytes
     */
    public static String getStringByBytes(byte[] sources, Charset charset) {
        if (sources == null || sources.length == 0) {
            return EMPTY_STRING;
        }

        return new String(sources, charset);
    }

    /**
     * Gets string by bytes.
     *
     * @param sources the sources
     * @param charset the charset
     * @return the string by bytes
     */
    public static String getStringByBytes(byte[] sources, String charset) {
        Charset encodingCharset = stringConvertCharset(charset);
        return getStringByBytes(sources, encodingCharset);
    }

    /**
     * Gets string by bytes and default encoding.
     *
     * @param sources the sources
     * @return the string by bytes and default encoding
     */
    public static String getStringByBytesAndDefaultEncoding(byte[] sources) {
        return getStringByBytes(sources, DEFAULT_CHARSET);
    }
}
