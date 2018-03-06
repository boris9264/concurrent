package com.boris.learn.concurrent.part1.demo02;

import net.jcip.annotations.ThreadSafe;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CachedFactorizer {

    //不使用AtomicLong效率更高
    private BigInteger lastNumber = BigInteger.ZERO;
    private BigInteger[] lastFactors;
    private long hits;
    private long cacheHits;

    public synchronized long getHits() {
        return hits;
    }

    public synchronized long getCacheHits() {
        return cacheHits;
    }

    public static void main(String[] args) {
        CachedFactorizer cachedFactorizer = new CachedFactorizer();
        cachedFactorizer.service1(BigInteger.valueOf(45));
    }

    public BigInteger[] service1(BigInteger i) {
        BigInteger[] factors = null;
        synchronized (this) {
            hits++;
            if(lastNumber.equals(i)) {
                cacheHits++;
                factors = lastFactors.clone();
            }
        }

        if(factors==null) {
            factors = factor(i);
            synchronized (this) {
                lastNumber = i;
                lastFactors = factors.clone();
            }
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
