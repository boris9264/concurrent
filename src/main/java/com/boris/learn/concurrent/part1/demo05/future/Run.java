package com.boris.learn.concurrent.part1.demo05.future;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        Computable<String, Integer> computable = new ComputableImpl();
        Memoizer<String, Integer> memoizer = new Memoizer<>(computable);

        Integer value = memoizer.compute("111");
        System.out.println(value);
        System.out.println("====================================>");
        value = memoizer.compute("111");
        System.out.println(value);
    }
}
