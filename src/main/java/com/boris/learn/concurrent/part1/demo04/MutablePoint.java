package com.boris.learn.concurrent.part1.demo04;

public class MutablePoint {
    public int x,y;
    public MutablePoint() {
        this.x = 0;
        this.y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
}
