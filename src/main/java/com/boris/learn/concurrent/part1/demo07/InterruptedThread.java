package com.boris.learn.concurrent.part1.demo07;

public class InterruptedThread extends Thread {
    @Override
    public void run() {
        System.out.println("begin....");
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("execute....");
        }
        if(Thread.currentThread().isInterrupted()) {
            System.out.println("interrupted.....");
        }
        System.out.println("end....");
    }

    public void cancel() {
        interrupt();
    }
}
