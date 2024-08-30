package model;

import model.enums.Priority;

import java.util.Objects;

public class Task {
    private static final Incrementator incrementator = new Incrementator();
    private int id;
    private String name;
    private Priority priority;

    public Task(String name, Priority priority) {
        this.name = name;
        this.priority = priority;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Task name: %s, task priority: %s".formatted(name, priority);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Task task = (Task) object;
        return Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public static Incrementator getIncrementator() {
        return incrementator;
    }
}
