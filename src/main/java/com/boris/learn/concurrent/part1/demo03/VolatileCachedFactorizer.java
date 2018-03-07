package com.boris.learn.concurrent.part1.demo03;

import java.math.BigInteger;

public class VolatileCachedFactorizer {
    private volatile OneValueCache cache = new OneValueCache(null, null);

    public BigInteger[] service(BigInteger i) {
        BigInteger[] factors = cache.getFactors(i);
        if (factors == null) {
            factors = factor(i);
            //每次重新创建对象，volatile修饰对其他线程是可见的
            cache = new OneValueCache(i, factors);
        }
        return factors;
    }

    public BigInteger[] factor(BigInteger req) {
        String reqStr = String.valueOf(req);
        BigInteger[] res = new BigInteger[reqStr.length()];
        for (int i = 0; i <reqStr.length() ; i++) {
            res[i] = BigInteger.valueOf(Long.valueOf(reqStr.substring(i,i+1)));
        }
        return res;
    }
}
