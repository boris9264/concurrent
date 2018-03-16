package com.boris.learn.concurrent.part1.demo05.semaphore;

import com.boris.learn.concurrent.JsonUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Run implements Runnable {
    private BoundHashSet<String> boundHashSet;
    private String value;
    private boolean remove;

    public Run(BoundHashSet<String> boundHashSet, String value, boolean remove) {
        this.boundHashSet = boundHashSet;
        this.value = value;
        this.remove = remove;
    }

    @Override
    public void run() {
        if(remove) {
            try {
                Thread.sleep(2000);
                System.out.println("remove....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boundHashSet.remove(value);
        } else {
            boolean flag = boundHashSet.add(value);
            System.out.println(flag);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        BoundHashSet<String> boundHashSet = new BoundHashSet<>(2);
        Run run = new Run(boundHashSet, "123", false);
        Run run1 = new Run(boundHashSet, "1234", false);
        Run run2 = new Run(boundHashSet, "1235", false);
        Run run3 = new Run(boundHashSet, "123", true);

        executorService.execute(run);
        executorService.execute(run1);
        executorService.execute(run2);
        executorService.execute(run3);
        Thread.sleep(3000);
        System.out.println(JsonUtil.toString(boundHashSet.getValue()));
    }
}
