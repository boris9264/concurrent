package com.boris.learn.concurrent.part1.demo04;

import com.boris.learn.concurrent.JsonUtil;

public class ListHelperRun {
    public static void main(String[] args) {
        ListHelperSafe<String> listHelper = new ListHelperSafe<>();
        listHelper.list.add("111");
        listHelper.list.add("222");

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                listHelper.putIfAbsent("888");
                System.out.println(JsonUtil.toString(listHelper.list));
            }
        });
        t.start();
        listHelper.list.set(0,"999");
        System.out.println(JsonUtil.toString(listHelper.list));
    }
}
