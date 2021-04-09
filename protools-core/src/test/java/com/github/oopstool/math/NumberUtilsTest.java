package com.github.oopstool.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.Test;

public class NumberUtilsTest {

    @Test
    public void add() {
        BigDecimal add = NumberUtils.add(1, 2.01d, 3L);
        System.out.println(add.toString());
        double add1 = NumberUtils.add(1d, 2d);
        System.out.println(add1);

        BigDecimal add2 = NumberUtils.add("1", "2.34");
        System.out.println(add2);
    }


    @Test
    public void sub() {
        BigDecimal sub = NumberUtils.sub(4, 1, 1.1);
        System.out.println(sub.toString());
        BigDecimal sub1 = NumberUtils.sub("4", "1", "4.3");
        System.out.println(sub1);
    }


    @Test
    public void mul() {
        BigDecimal mul = NumberUtils.mul(1.1, 3.3333, 4L);
        System.out.println(mul.toString());
    }


    @Test
    public void div() {
        double div = NumberUtils.div(14, 3, 2, RoundingMode.HALF_UP);
        System.out.println(div);
    }

}