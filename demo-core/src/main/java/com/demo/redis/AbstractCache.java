package com.demo.redis;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: jiuzeng.sun
 * Email: jiuzeng.sun@leappmusic.com
 * Time: 2016/3/31.
 */
public abstract class AbstractCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCache.class);

    protected abstract String getBaseCacheKey();

    public interface RedisCacheCallback<T> {
        T getValue();
    }

    protected abstract CommonRedisCache getRedisCache();

    protected <T> T getCacheValue(String cacheKey, Class<T> valueType) {
        LOGGER.debug("getCacheValue cacheKey is : {}", cacheKey);

        String key = getBaseCacheKey() + cacheKey() + cacheKey;
        String value = getRedisCache().get(key);
        LOGGER.debug("getCacheValue redis value is : {}", value);

        if (StringUtils.isNotBlank(value)) {
            LOGGER.debug("getCacheValue redis value is not null");
            return JSON.parseObject(value, valueType);
        } else {
            try {
                return valueType.newInstance();
            } catch (InstantiationException e) {
                LOGGER.error("Error!", e);
            } catch (IllegalAccessException e) {
                LOGGER.error("Error!", e);
            }
        }

        return null;
    }

    protected <T> List<T> getListCacheValue(String cacheKey, Class<T> valueType, RedisCacheCallback<List<T>> cacheCallback) {
        LOGGER.debug("getCacheValue cacheKey is : {}", cacheKey);

        String key = getBaseCacheKey() + cacheKey() + cacheKey;
        String value = getRedisCache().get(key);
        LOGGER.debug("getCacheValue redis value is : {}", value);

        List<T> listValue;
        if (StringUtils.isNotBlank(value)) {
            LOGGER.debug("getCacheValue redis value is not null");
            listValue = JSON.parseArray(value, valueType);
            if (CollectionUtils.isNotEmpty(listValue)) {
                return listValue;
            }
        }

        listValue = cacheCallback.getValue();
        if (CollectionUtils.isNotEmpty(listValue)) {
            setCacheValue(cacheKey, listValue);
            return listValue;
        }

        return null;
    }

    protected <T> T getObjectCacheValue(String cacheKey, Class<T> valueType, RedisCacheCallback<T> cacheCallback) {
        String key = getBaseCacheKey() + cacheKey() + cacheKey;
        String value = getRedisCache().get(key);
        LOGGER.info("getCacheValue redis key is {}, value is : {}", key, value);

        T objectValue;
        if (StringUtils.isNotBlank(value)) {
            LOGGER.info("getCacheValue redis value is not null");
            objectValue = JSON.parseObject(value, valueType);
            return objectValue;
        }

        objectValue = cacheCallback.getValue();
        if (objectValue != null) {
            setCacheValue(cacheKey, objectValue);
            return objectValue;
        }

        return null;
    }

    protected <K, V> Map<K, V> mGet(Collection<K> keys, Class<V> valueType) {
        List<String> inputKeys = Lists.newArrayList();
        Map<String, K> tempKeys = Maps.newHashMap();
        for (K cacheKey : keys) {
            String key = getBaseCacheKey() + cacheKey() + cacheKey;
            inputKeys.add(key);
            tempKeys.put(key, cacheKey);
        }
        Map<K, V> result = Maps.newHashMap();
        Map<String, String> values = getRedisCache().mGet(inputKeys);
        for (Map.Entry<String, String> entry : values.entrySet()) {
            String value = entry.getValue();
            if (value != null) {
                result.put(tempKeys.get(entry.getKey()), JSON.parseObject(value, valueType));
            }
        }

        return result;
    }

    protected void setCacheValue(String cacheKey, Object cacheValue) {
        if (StringUtils.isBlank(cacheKey) || cacheValue == null) {
            return;
        }

        String value = JSON.toJSONString(cacheValue);
        if (StringUtils.isNotBlank(value)) {
            String key = getBaseCacheKey() + cacheKey() + cacheKey;
            getRedisCache().set(key, value, maxCacheAge());
            LOGGER.info("setCacheValue key is :{}, cacheValue is :{}", key, value);
        }
    }

    protected void clearCache(String cacheKey) {
        String key = getBaseCacheKey() + cacheKey() + cacheKey;
        getRedisCache().delete(key);
        LOGGER.info("delete cache key is {}", key);
    }

    protected void clearCachePrefix(String prefix){
        String key = getBaseCacheKey() + cacheKey() + prefix;
        Set<String> keys = getRedisCache().matchKeys(key);
        getRedisCache().delete(keys.toArray(new String[]{}));
    }

    protected <T> T getHashCacheValue(String cacheKey, String hKey, Class<T> valueType) {
        LOGGER.debug("getHashCacheValue cacheKey is : {}", cacheKey);

        String key = getBaseCacheKey() + cacheKey() + cacheKey;
        String value = getRedisCache().getHash(key, hKey);
        LOGGER.debug("getHashCacheValue redis value is : {}", value);

        if (StringUtils.isNotBlank(value)) {
            LOGGER.debug("getHashCacheValue redis value is not null");
            return JSON.parseObject(value, valueType);
        }

        return null;
    }

    protected void setHashCacheValue(String cacheKey, String hKey, Object cacheValue) {
        LOGGER.debug("setHashCacheValue cacheKey is : {}", cacheKey);
        if (StringUtils.isBlank(cacheKey) || cacheValue == null) {
            return;
        }

        LOGGER.debug("setHashCacheValue cacheKey is : {}, cacheValue is not null", cacheKey);

        String value = JSON.toJSONString(cacheValue);
        LOGGER.debug("cacheValue is : {}", value);
        if (StringUtils.isNotBlank(value)) {
            String key = getBaseCacheKey() + cacheKey() + cacheKey;
            getRedisCache().setHash(key, hKey, value);
            LOGGER.debug("setHashCacheValue cacheKey is :{}, cacheValue is :{}", cacheKey, value);
        }
    }

    protected void clearHashCache(String cacheKey, String hKey) {
        String key = getBaseCacheKey() + cacheKey() + cacheKey;
        getRedisCache().delHash(key, hKey);
    }

    /**
     * 缓存的key
     *
     * @return
     */
    protected abstract String cacheKey();

    /**
     * 缓存的最大时间
     *
     * @return
     */
    protected abstract int maxCacheAge();

}
