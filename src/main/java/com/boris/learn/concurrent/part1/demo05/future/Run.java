package com.boris.learn.concurrent.part1.demo05.future;

public class Run {
    public static void main(String[] args) {
        Computable<String, Integer> computable = new ComputableImpl();
        Memoizer<String, Integer> memoizer = new Memoizer<>(computable);
        Integer value = memoizer.compute("1123");
        System.out.println(value);
        value = memoizer.compute("1123");
        System.out.println(value);


    }
}
