package model;

public class Incrementator {
    private int currentId;
    public int incrementAndSet() {
        return ++currentId;
    }
}
