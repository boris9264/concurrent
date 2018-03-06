package com.boris.learn.concurrent.part1.demo01;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class UnsafeCountingFactorizer {
/*
    修改该变量时可能有其他线程读取该变量值，
    要保证该变量的操作，必须是原子性的或不可分割的（目前该变量的操作是复合操作）
*/
    private long count = 0;

    public long getCount() {
        return count;
    }

    public void init() {
        ++count;
    }
}
