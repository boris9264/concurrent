package com.boris.learn.concurrent.part1.demo02;

import net.jcip.annotations.ThreadSafe;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class SynchronizedFactorizer {
    private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();

    private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<>();

    //synchronized(互斥锁)锁住的是对象，而不是代码
    public synchronized BigInteger[] service(BigInteger i) {
        if(!i.equals(lastNumber.get())) {
            BigInteger[] factors = this.factor(i);
            lastFactors.set(factors);
            lastNumber.set(i);
        }
        return lastFactors.get();
    }

    //可以保证该段代码同步执行
    public BigInteger[] service1(BigInteger i) {
        synchronized (SynchronizedFactorizer.class) {
            if(!i.equals(lastNumber.get())) {
                BigInteger[] factors = this.factor(i);
                lastFactors.set(factors);
                lastNumber.set(i);
            }
            return lastFactors.get();
        }
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
