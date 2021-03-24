package org.oops.protools.core.string;

import org.junit.Assert;
import org.junit.Test;
import org.oops.protools.core.security.RandomUtils;

/**
 * 随机数测试类
 *
 * @author : HouGY
 * @since : 2021/3/18
 */
public class RandomUtilsTest {

    @Test
    public void randomString() {
        //指定字符串样本获取随机字符串
        String baseString = "sdfjaljgklaje12334SDDwwqw";
        String randomString = RandomUtils.randomString(baseString, 0);
        assert randomString.length() == 2;
        //获取随机字符串（数字和字母）
        String randomString1 = RandomUtils.randomString(4);
        assert randomString1.length() == 4;
    }

    @Test
    public void randomNumbers() {
        String numbers = RandomUtils.randomNumbers(6);
        assert numbers.length() == 6;
        Integer.parseInt(numbers);
    }

    @Test
    public void randomInt() {
        //随机int
        int randomInt = RandomUtils.randomInt();
        Assert.assertNotNull(randomInt);
        //指定范围随机int
        int randomInt1 = RandomUtils.randomInt(10);
        assert 0 <= randomInt1 && randomInt1 < 10;
        //指定范围随机int
        int randomInt2 = RandomUtils.randomInt(6, 10);
        assert 6 <= randomInt2 && randomInt2 < 10;
    }


    @Test
    public void randomLong() {
        //随机long
        long randomInt = RandomUtils.randomLong();
        Assert.assertNotNull(randomInt);
        //指定范围随机long
        long randomInt1 = RandomUtils.randomLong(10);
        assert 0 <= randomInt1 && randomInt1 < 10;
        //指定范围随机long
        long randomInt2 = RandomUtils.randomLong(6, 10);
        assert 6 <= randomInt2 && randomInt2 < 10;
    }

    @Test
    public void randomDouble() {
        //随机double
        double randomInt = RandomUtils.randomDouble();
        Assert.assertNotNull(randomInt);
        //指定范围随机double
        double randomInt1 = RandomUtils.randomDouble(10);
        assert 0 <= randomInt1 && randomInt1 < 10;
        //指定范围随机double
        double randomInt2 = RandomUtils.randomDouble(6, 10);
        assert 6 <= randomInt2 && randomInt2 < 10;
    }

}
