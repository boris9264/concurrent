package com.boris.learn.concurrent.part1.demo04;

public class NumberRangeRun {
    public static void main(String[] args) {
        NumberRange numberRange = new NumberRange();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                numberRange.setLower(0);
            }
        });

        NumberRange numberRange1 = new NumberRange();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                numberRange1.setUpper(100);
            }
        });
        t1.start();
        t2.start();
    }
}
