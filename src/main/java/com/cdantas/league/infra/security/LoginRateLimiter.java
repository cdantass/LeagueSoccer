package com.cdantas.league.infra.security;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LoginRateLimiter {

    private static final int MAX_ATTEMPTS = 5;
    private static final long WINDOW_SECONDS = 300; // 5 minutos

    private final ConcurrentHashMap<String, Attempt> attemptsByKey = new ConcurrentHashMap<>();

    public boolean isBlocked(String key) {
        Attempt attempt = attemptsByKey.get(key);
        if (attempt == null) return false;

        if (Instant.now().getEpochSecond() - attempt.windowStart > WINDOW_SECONDS) {
            attemptsByKey.remove(key);
            return false;
        }

        return attempt.count.get() >= MAX_ATTEMPTS;
    }

    public void registerFailure(String key) {
        attemptsByKey.compute(key, (k, existing) -> {
            if (existing == null || Instant.now().getEpochSecond() - existing.windowStart > WINDOW_SECONDS) {
                return new Attempt(Instant.now().getEpochSecond());
            }
            existing.count.incrementAndGet();
            return existing;
        });
    }

    public void reset(String key) {
        attemptsByKey.remove(key);
    }

    private static class Attempt {
        final long windowStart;
        final AtomicInteger count = new AtomicInteger(1);

        Attempt(long windowStart) {
            this.windowStart = windowStart;
        }
    }
}