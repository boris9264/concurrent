package com.boris.learn.concurrent.part1.demo05.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CycWork implements Runnable {
    private CyclicBarrier cyclicBarrier;
    private String name;

    public CycWork(CyclicBarrier cyclicBarrier, String name) {
        this.cyclicBarrier = cyclicBarrier;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + ":正在打桩...");
        try {
            Thread.sleep(5000);
            System.out.println(name + ":打桩完毕...");
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println(name + ":其他工人打桩完毕,继续造桥...");

        cyclicBarrier.reset();

        try {
            Thread.sleep(5000);
            System.out.println(name + ":造桥完毕...");
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println(name + ":其他工人造桥完毕，一起吃饭...");
    }
}
