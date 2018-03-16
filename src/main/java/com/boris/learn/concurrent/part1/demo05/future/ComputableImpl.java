package com.boris.learn.concurrent.part1.demo05.future;

public class ComputableImpl implements Computable<String, Integer> {
    @Override
    public Integer compute(String arg) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Integer.valueOf(arg);
    }
}
