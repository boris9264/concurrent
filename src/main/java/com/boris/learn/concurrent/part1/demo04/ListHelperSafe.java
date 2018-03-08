package com.boris.learn.concurrent.part1.demo04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListHelperSafe<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    //对list加锁可以保证执行该方法时，list不会被其他线程修改
    public boolean putIfAbsent(E x) {
        synchronized (list) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean absent = !list.contains(x);
            if(absent) {
                list.add(x);
            }
            return absent;
        }
    }
}
