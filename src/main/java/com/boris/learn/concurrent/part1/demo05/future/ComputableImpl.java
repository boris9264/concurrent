package com.boris.learn.concurrent.part1.demo05.future;

import org.apache.catalina.LifecycleState;

import java.util.ArrayList;
import java.util.List;

public class ComputableImpl implements Computable<String, Integer> {
    @Override
    public Integer compute(String arg) {
        if(arg.equals("111")) {
            List list = new ArrayList();
            System.out.println(list.get(2));
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Integer.valueOf(arg);
    }
}
