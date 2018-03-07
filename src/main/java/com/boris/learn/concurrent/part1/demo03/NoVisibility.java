package com.boris.learn.concurrent.part1.demo03;

public class NoVisibility {
    /*
        对于读线程来说ready的值可能永远不可见。
        也可能在对number赋值之前主线程就已经
        写入ready并使之对读线程可见（重排序）
    */
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while(!ready)
                Thread.yield();
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}
