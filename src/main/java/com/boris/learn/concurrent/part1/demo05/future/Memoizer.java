package com.boris.learn.concurrent.part1.demo05.future;

import java.util.Map;
import java.util.concurrent.*;

public class Memoizer<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> computable;

    public Memoizer(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A arg) {
        Future<V> future = cache.get(arg);
        if (future==null) {
            Callable<V> callable = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return computable.compute(arg);
                }
            };

            FutureTask<V> futureTask = new FutureTask<V>(callable);
            future = futureTask;
            cache.put(arg, future);
            futureTask.run();
        }
        V v = null;
        try {
            v = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            cache.remove(arg);
            e.printStackTrace();
        }
        return v;
    }
}
