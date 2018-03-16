package com.boris.learn.concurrent.part1.demo05.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Run {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(3);

        Worker w1 = new Worker(countDownLatch, "张三");
        Worker w2 = new Worker(countDownLatch, "李四");
        Worker w3 = new Worker(countDownLatch, "王五");

        Boss boss = new Boss(countDownLatch);

        executorService.execute(boss);
        executorService.execute(w1);
        executorService.execute(w2);
        executorService.execute(w3);

    }
}
