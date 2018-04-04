package com.boris.learn.concurrent.part1.demo07;

public class InterruptedRun {
    public static void main(String[] args) {
        InterruptedThread interruptedThread = new InterruptedThread();
        interruptedThread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        interruptedThread.cancel();
    }
}
