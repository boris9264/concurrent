package com.boris.learn.concurrent.part1.demo04;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MonitorVehicleTracker {
    //对象封装了所有的可变状态，并由对象自己的内部锁保护
    private final Map<String,MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint mutablePoint = locations.get(id);
        return mutablePoint==null ? null : new MutablePoint(mutablePoint);
    }

    public synchronized void setLocation(String id, int x, int y) {
        MutablePoint mutablePoint = locations.get(id);
        mutablePoint.x = x;
        mutablePoint.y = y;
    }
    private Map<String,MutablePoint> deepCopy(Map<String,MutablePoint> m) {
        Map<String,MutablePoint> result = new HashMap<>();
        for (String id : m.keySet()) {
            result.put(id,new MutablePoint(m.get(id)));
        }
        return Collections.unmodifiableMap(result);
    }
}
