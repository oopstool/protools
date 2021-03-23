package org.oops.protools.core.string;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.Lists;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 基于guava缓存工具类
 *
 * @author : HouGY
 * @since : 2021/3/22
 */
public class CacheUtils {

    /**
     * buildCache
     *
     * expireAfterWrite是在指定项在一定时间内没有创建/覆盖时，会
     * 移除该key，下次取的时候从loading中取
     * expireAfterAccess是指定项在一定时间内没有读写，会移除该key，下次取的时候从loading中取
     * refreshAfterWrite是在指定时间内没有被创建/覆盖，则指定时间过后，再次访问时，会去刷新该缓存，在新值没有到来之前，始终返回旧值
     * 跟expire的区别是，指定时间过后，expire是remove该key，下次访问是同步去获取返回新值；
     * 而refresh则是指定时间后，不会remove该key，下次访问会触发刷新，新值没有回来时返回旧值
     *
     * 设置的时候，可以让 expireAfterWrite > refreshAfterWrite, 这样每间隔refreshAfterWrite时间，当有访问的时候，进行refresh,
     * 如果超过 expireAfterWrite 没有访问，则让缓存失效， 这样可以同时利用guava cache的刷新机制和过期机制
     *
     * @param <K>
     * @param <V>
     * @param expireAfterWrite
     *            设置写缓存后过期时间，一个请求进行加载操作，其它请求阻塞
     * @param refreshAfterWrite
     *            设置写缓存后刷新时间，一个请求进行刷新操作，其它请求返回旧值
     * @param concurrencyLevel
     *            允许同时并发更新操作数。是指对一个缓存中的数据进行更新操作时的并发量。
     *            设置这个参数后，允许并发的最大量不一定会严格遵守这个参数。因为数据被分别存储到不同的区块中，而这些数据并不是均匀分布的。
     *            在代码实现中，缓存在将会根据这个参数创建对应的ConcurrentMap个数，每一个ConcurrentMap称为一个区块。
     *            数据会分别存储到每个ConcurrentMap上，还会有另外一个数据结构来维护所有缓存数据所在的位置。
     *            因此，如果将这个参数设置过大，会导致更多时间和空间上的开销（分配了更多的区块，需要额外维护这些区块信息）；
     *            如果设置过小，会导致在更新操作时，有大量的线程阻塞（更新同一个ConcurrentMap需要等待锁）
     * @param initialCapacity
     *            指定用于缓存的hash table最低总规模。——例如设置了initialCapacity为60，还设置了concurrencyLevel（参阅下文说明）为8。
     *            将会把存储的空间分为8块，每块都有一个hash table结构，每个hash table的初始规模为8。如果缓存空间有限，
     *            需要预估足够大的初始化空间来缓，避免在数据增长时昂贵的扩展操作（扩展空间会导致深度COPY）
     * @param maximumSize
     *            允许最大的缓存条目数
     * @param cacheLoader
     *            缓存加载逻辑
     * @param removalListener
     * @return
     */
    public static <K, V> LoadingCache<K, V> buildCache(Integer expireAfterWrite, Integer refreshAfterWrite,
        Integer concurrencyLevel, Integer initialCapacity, Integer maximumSize, CacheLoader<K, V> cacheLoader,
        RemovalListener removalListener) {
        LoadingCache<K, V> cache =
            CacheBuilder
                .newBuilder()
                .concurrencyLevel(concurrencyLevel)
                .expireAfterWrite(expireAfterWrite, TimeUnit.SECONDS)
                .refreshAfterWrite(refreshAfterWrite, TimeUnit.SECONDS)
                .initialCapacity(initialCapacity)
                .maximumSize(maximumSize)
                .recordStats()
                .removalListener(removalListener).build(cacheLoader);
        return cache;
    }

    public static void main1(String[] args) throws Exception {
        CacheLoader<String, List<String>> cacheLoader = new CacheLoader<String, List<String>>() {
            @Override
            public List<String> load(String key) {
                return loadCache(key);
            }
        };
        RemovalListener removalListener = new RemovalListener() {
            @Override
            public void onRemoval(RemovalNotification removalNotification) {
                System.out.println(removalNotification.getKey() + " was removed，the reason is:"
                    + removalNotification.getCause());
            }
        };
        LoadingCache<String,List<String>> loadingCache = buildCache(2,1, 1, 32,100, cacheLoader,removalListener);

        System.out.println(loadingCache.get("1"));
        Thread.sleep(6000);
        System.out.println("===="+loadingCache.get("1"));
    }

    public static List<String> loadCache(String key){
        System.out.println("loadCache begin");
        List<String> list = Lists.newArrayList("AAA", "BBB");
        System.out.println("loadCache end");
        return list;
    }

    //main函数测试用
    public static void main(String[] args) {
        //获取所有的数据库驱动
        Enumeration<Driver> driverEnum = DriverManager.getDrivers();
        //打印出所有驱动信息
        while(driverEnum.hasMoreElements()){
            System.out.println(driverEnum.nextElement());
        }
    }

}
