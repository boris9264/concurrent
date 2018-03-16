package com.boris.learn.concurrent.part1.demo05.latch;

import java.util.concurrent.CountDownLatch;

public class Boss implements Runnable {
    private CountDownLatch countDownLatch;

    public Boss(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("等待所有工人干完活...");
        try {
            this.countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("工人干完活，开始验收...");
    }
}
