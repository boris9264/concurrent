package com.boris.learn.concurrent.part1.demo04;

import java.util.HashMap;
import java.util.Map;

public class DelegatingRun {
    public static void main(String[] args) {
        Map<String, Point> points = new HashMap<>();
        points.put("1001", new Point(10, 20));
        points.put("2001", new Point(20, 30));

        DelegatingVehicleTracker tracker = new DelegatingVehicleTracker(points);

        Map<String, Point> pointsRes = tracker.getLocations();
        pointsRes.put("3001", new Point(30, 40));
    }
}
