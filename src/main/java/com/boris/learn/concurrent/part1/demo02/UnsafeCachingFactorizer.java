package com.boris.learn.concurrent.part1.demo02;

import net.jcip.annotations.NotThreadSafe;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

@NotThreadSafe
public class UnsafeCachingFactorizer {
    private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();

    private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<>();

    //存在竞态条件，非线程安全
    public BigInteger[] service(BigInteger i) {
        if(!i.equals(lastNumber.get())) {
            BigInteger[] factors = this.factor(i);
            lastFactors.set(factors);
            lastNumber.set(i);
        }
        return lastFactors.get();
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
