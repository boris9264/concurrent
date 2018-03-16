package com.boris.learn.concurrent.part1.demo05.future;

public interface Computable<A, V> {
    V compute(A arg);
}
