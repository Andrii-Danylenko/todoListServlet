package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Incrementator {

    private final AtomicInteger currentId = new AtomicInteger(1);
    public synchronized int incrementAndGet() {
        return currentId.getAndIncrement();
    }

    public synchronized int getCurrentId() {
        return currentId.get();
    }
}
