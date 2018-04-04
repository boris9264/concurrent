package com.boris.learn.concurrent.part1.demo05.barrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Run {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        CycWork cycWork = new CycWork(cyclicBarrier, "张三");
        CycWork cycWork1 = new CycWork(cyclicBarrier, "李四");
        CycWork cycWork2 = new CycWork(cyclicBarrier, "王五");
        executorService.execute(cycWork);
        executorService.execute(cycWork1);
        executorService.execute(cycWork2);
        executorService.shutdown();
    }
}
