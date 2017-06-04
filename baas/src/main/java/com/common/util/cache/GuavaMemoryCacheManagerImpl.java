package com.common.util.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.baas.common.util.log.LoggerUtil;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于封装Guava cache的实现类
 * 
 * 
 * @version $Id: GuavaMemoryCacheManagerImpl.java, v 0.1 2015年10月10日 上午11:07:01  Exp $
 */
public class GuavaMemoryCacheManagerImpl implements MemoryCacheManager {
    private static final Logger                      LOGGER                     = LoggerFactory.getLogger(GuavaMemoryCacheManagerImpl.class);

    /**
     * 默认缓存的过期时间5分钟
     */
    public static final long                         DEFAULT_EXPIRE_TIME        = 1 * 60L;
    /**
     * DEFAULT_CACHE_SIZE
     */
    public static final long                         DEFAULT_CACHE_SIZE         = 10000L;

    /**
     * GUAVA_MEMORY_DEFAULT_CACHE
     */
    public static final String                       GUAVA_MEMORY_DEFAULT_CACHE = "GUAVA_MEMORY_DEFAULT_CACHE";

    private final Map<String, Cache<Object, Object>> cacheMap                   = new ConcurrentHashMap<String, Cache<Object, Object>>();

    private final Cache<Object, Object>              cache;

    /**
     * GuavaMemoryCacheManagerImpl
     */
    public GuavaMemoryCacheManagerImpl() {
        cache = createCacheInternal(GUAVA_MEMORY_DEFAULT_CACHE, DEFAULT_EXPIRE_TIME);
    }

    /**
     * 用于指定缓存的过期时间，创建给定名称和过期时间的缓存
     * 
     * @param name
     * @param expireTime in seconds
     */
    @Override
    public void createCache(String name, long expireTime) {
        createCacheInternal(name, expireTime);
    }

    /**
     * createCacheInternal
     * 
     * @param name
     * @param expireTime
     * @return
     */
    public Cache<Object, Object> createCacheInternal(String name, long expireTime) {
        Cache<Object, Object> cache = cacheMap.get(name);
        if (cache == null) {
            cache = CacheBuilder.newBuilder().maximumSize(DEFAULT_CACHE_SIZE).expireAfterWrite(expireTime, TimeUnit.SECONDS).softValues().build();
            cacheMap.put(name, cache);
            LoggerUtil.debug(LOGGER, "创建Guava Cache: ", name, " expireTime=", expireTime);
        } else {
            LoggerUtil.debug(LOGGER, "Guava Cache已存在:", name);
        }
        return cache;
    }

    /** 
     * @see com.common.util.cache.MemoryCacheManager#put(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void put(String name, Object key, Object value) {
        Cache<Object, Object> cache = cacheMap.get(name);
        if (cache == null) {
            cache = createCacheInternal(name, DEFAULT_EXPIRE_TIME);
        }
        cache.put(key, value);
    }

    /** 
     * @see com.common.util.cache.MemoryCacheManager#get(java.lang.String, java.lang.Object)
     */
    @Override
    public Object get(String name, Object key) {
        Cache<Object, Object> c = cacheMap.get(name);
        if (c == null) {
            return null;
        }
        return c.getIfPresent(key);
    }

    /** 
     * @see com.common.util.cache.MemoryCacheManager#put(java.lang.Object, java.lang.Object)
     */
    @Override
    public void put(Object key, Object value) {
        cache.put(key, value);
    }

    /** 
     * @see com.common.util.cache.MemoryCacheManager#get(java.lang.Object)
     */
    @Override
    public Object get(Object key) {
        return cache.getIfPresent(key);
    }

    /** 
     * @see com.common.util.cache.MemoryCacheManager#remove(java.lang.Object)
     */
    @Override
    public void remove(Object key) {
        cache.invalidate(key);
    }

}
