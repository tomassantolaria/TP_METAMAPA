package RateLimit;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiter {
    private final int maxRequests;
    private final int windowSeconds;

    private Instant windowStart;
    private final AtomicInteger requestCount;

    public RateLimiter(int maxRequests, int windowSeconds) {
        this.maxRequests = maxRequests;
        this.windowSeconds = windowSeconds;
        this.windowStart = Instant.now();
        this.requestCount = new AtomicInteger(0);
    }

    public synchronized boolean tryAcquire() {
        Instant now = Instant.now();

        if (now.isAfter(windowStart.plusSeconds(windowSeconds))) {
            windowStart = now;
            requestCount.set(0);
        }

        if (requestCount.incrementAndGet() <= maxRequests) {
            return true;
        }

        return false;
    }
}
