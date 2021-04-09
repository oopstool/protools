package com.github.oopstool.algorithm;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import java.nio.charset.Charset;
import java.util.HashSet;
import org.junit.Assert;
import org.junit.Test;

public class SnowflakeTest {

    public static void main(String[] args) {
        //1%，有个概率问题，布隆越大，占用的空间越多，但是错误概率减小了
        BloomFilter bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), 1000000, 0.001);
        bloomFilter.put("111");
        //为true表示在布隆过滤器里
        System.out.println(bloomFilter.mightContain("111"));
    }


    @Test
    public void snowflakeTest1() {
        //构建Snowflake，提供终端ID和数据中心ID
        Snowflake idWorker = new Snowflake(0, 0);
        long nextId = idWorker.nextId();
        Assert.assertTrue(nextId > 0);
    }

    @Test
    public void snowflakeTest() {
        HashSet<Long> hashSet = new HashSet<>();

        //构建Snowflake，提供终端ID和数据中心ID
        Snowflake idWorker = new Snowflake(0, 0);
        for (int i = 0; i < 1000; i++) {
            long id = idWorker.nextId();
            hashSet.add(id);
        }
        Assert.assertEquals(1000L, hashSet.size());
    }

    @Test
    public void snowflakeGetTest() {
        //构建Snowflake，提供终端ID和数据中心ID
        Snowflake idWorker = new Snowflake(1, 2);
        long nextId = idWorker.nextId();

        Assert.assertEquals(1, idWorker.getWorkerId(nextId));
        Assert.assertEquals(2, idWorker.getDataCenterId(nextId));
        Assert.assertTrue(idWorker.getGenerateDateTime(nextId) - System.currentTimeMillis() < 10);
    }
}
