package com.boris.learn.concurrent.part1.demo04;

import java.util.concurrent.atomic.AtomicInteger;

public class NumberRange {
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public  void setLower(int i) {
        synchronized(NumberRange.class) {
            try {
                System.out.println("setLower begin...");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //不安全的 检查再运行
            if(i > upper.get()) {
                throw new IllegalArgumentException("can't set lower to " + i + " > upper");
            } else {
                lower.set(i);
            }
            System.out.println("setLower end...");
        }
    }

    public void setUpper(int i) {
        synchronized(NumberRange.class) {
            try {
                System.out.println("setUpper.......");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //不安全的 检查再运行
            if(i < lower.get()) {
                throw new IllegalArgumentException("can't set upper to " + i + " < lower");
            }
        }

    }

    public boolean isInRange(int i) {
        return i>=lower.get() && i<=upper.get();
    }
}
