package com.boris.learn.concurrent.part1.demo01;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicLong;

@ThreadSafe
public class CountingFactorizer {
    //原子变量
    private final AtomicLong count = new AtomicLong(0);

    public long getCount() {
        return count.get();
    }

    public void init() {
        //递增加一并返回结果
        count.incrementAndGet();
    }
}
