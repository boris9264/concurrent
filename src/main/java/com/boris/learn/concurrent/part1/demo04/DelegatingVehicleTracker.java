package com.boris.learn.concurrent.part1.demo04;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DelegatingVehicleTracker {
    private final ConcurrentMap<String, Point> locations;

    private final Map<String, Point> unmodifiableMap;

    //unmodifiableMap会随locations的变化而变化，保证能够获取最新值
    public DelegatingVehicleTracker(Map<String, Point> points) {
        this.locations = new ConcurrentHashMap<>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations() {
//        return Collections.unmodifiableMap(new HashMap(locations));
        return unmodifiableMap;
    }

    public Point getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        locations.replace(id, new Point(x, y));
    }
}
