package com.example.inspirationalquotes.util;

import org.springframework.stereotype.Component;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimiter {
    private final int LIMIT = 5; // 5 requests
    private final long WINDOW = 60 * 1000; // 1 minute
    private final Map<String, List<Long>> requestLog = new ConcurrentHashMap<>();

    public boolean isAllowed(String ip) {
        long now = System.currentTimeMillis();
        requestLog.putIfAbsent(ip, new ArrayList<>());

        List<Long> timestamps = requestLog.get(ip);
        synchronized (timestamps) {
            timestamps.removeIf(time -> (now - time) > WINDOW);

            if (timestamps.size() >= LIMIT) {
                return false;
            } else {
                timestamps.add(now);
                return true;
            }
        }
    }

    public long getRetryAfter(String ip) {
        List<Long> timestamps = requestLog.get(ip);
        if (timestamps == null || timestamps.isEmpty()) return 0;
        long now = System.currentTimeMillis();
        return (WINDOW - (now - timestamps.get(0))) / 1000;
    }
}