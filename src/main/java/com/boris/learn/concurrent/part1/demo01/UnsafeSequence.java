package com.boris.learn.concurrent.part1.demo01;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class UnsafeSequence {
    private static int value;

    /*返回一个唯一值*/
    public static int getNext() {
        return value++;
    }

    public static void main(String[] args) {
        for (int i = 0; i <1000 ; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(UnsafeSequence.getNext());
                }
            });
            thread.start();
        }
    }

}
