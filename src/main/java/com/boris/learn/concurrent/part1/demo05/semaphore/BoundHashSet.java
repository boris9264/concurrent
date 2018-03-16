package com.boris.learn.concurrent.part1.demo05.semaphore;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class BoundHashSet<T> {
    private final Set<T> set;
    private final Semaphore semaphore;

    public BoundHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<>());
        this.semaphore = new Semaphore(bound);
    }

    public boolean add(T t) {
        boolean wasAdded = false;
        try {
            semaphore.acquire();
            wasAdded = set.add(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(!wasAdded) {
                semaphore.release();
            }
        }
        return wasAdded;
    }

    public boolean remove(T t) {
        boolean wasRemoved = set.remove(t);
        if (wasRemoved) {
            semaphore.release();
        }
        return wasRemoved;
    }

    public Set<T> getValue() {
        return set;
    }
}
