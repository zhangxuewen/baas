package com.common.util.cache;

/**
 * 本地内存缓存 
 * 
 * @author 
 * @version $Id: MemoryCacheManager.java, v 0.1 2015年10月10日 下午3:12:44 Exp $
 */
public interface MemoryCacheManager {

    /**
     * 从默认缓存中获取对象
     * 
     * @param key
     * @return
     */
    public Object get(Object key);

    /**
     * 向默认缓存中放置对象
     * 
     * @param key
     * @param value
     */
    public void put(Object key, Object value);

    /**
     * 从给定名称的缓存中获取对象，该缓存通过{@link #createCache(String, long)}创建
     * 
     * @param name
     * @param key
     * @return
     */
    public Object get(String name, Object key);

    /**
     * 向给定名称的缓存中放置对象，该命名缓存通过{@link #createCache(String, long)}创建
     * 
     * @param name
     * @param key
     * @param value
     */
    public void put(String name, Object key, Object value);

    /**
     * 用于指定缓存的过期时间，创建给定名称和过期时间的缓存
     * 
     * @param name
     * @param expireTime in seconds
     */
    public void createCache(String name, long expireTime);

    /**
     * 删除缓存
     * @param key
     */
    public void remove(Object key);

}
