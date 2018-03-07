package com.boris.learn.concurrent.part1.demo03;

import com.boris.learn.concurrent.JsonUtil;

import java.math.BigInteger;
import java.util.Arrays;

public class OneValueCache {
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger lastNumber, BigInteger[] lastFactors) {
        this.lastNumber = lastNumber;
        if (lastFactors!=null) {
            this.lastFactors = Arrays.copyOf(lastFactors, lastFactors.length);
        } else {
            this.lastFactors = null;
        }

    }

    public BigInteger[] getFactors(BigInteger i) {
        if (lastNumber==null || !lastNumber.equals(i)) {
            return null;
        } else {
            return Arrays.copyOf(lastFactors, lastFactors.length);
        }
    }

    public BigInteger[] getLastFactors() {
        return lastFactors;
    }

    public static void main(String[] args) {
        OneValueCache cache = new OneValueCache(BigInteger.valueOf(921), new BigInteger[]{BigInteger.valueOf(9), BigInteger.valueOf(2), BigInteger.valueOf(1)});
        BigInteger[] arr1 = cache.getFactors(BigInteger.valueOf(921));
        arr1[0] = BigInteger.valueOf(921);
        System.out.println(JsonUtil.toString(arr1));

        BigInteger[] arr2 = cache.getLastFactors();
        System.out.println(JsonUtil.toString(arr2));
    }
}
