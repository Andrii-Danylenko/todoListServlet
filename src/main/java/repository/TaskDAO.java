package repository;

import model.Task;
import model.enums.Priority;

import java.util.*;

public class TaskDAO implements DAO<Task> {
    private final LinkedHashSet<Task> tasks = new LinkedHashSet<>();
    private static TaskDAO INSTANCE;


    private TaskDAO(){}

    public synchronized static TaskDAO getInstance() {
        if (INSTANCE == null) {
            return INSTANCE = new TaskDAO();
        }
        return INSTANCE;
    }

    @Override
    public boolean add(Task value) {
        if (tasks.contains(value)) {
            return false;
        }
        else {
            value.setId(Task.getIncrementator().incrementAndGet());
            return tasks.add(value);
        }
    }

    @Override
    public boolean delete(Task value) {
        if (tasks.remove(value)) {
            System.out.println("Successfully deleted task!");
            return true;
        }
        else {
            throw new RuntimeException("No such user found!");
        }
    }

    @Override
    public Task getById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        throw new RuntimeException("No such user found!");
    }

    @Override
    public Set<Task> getAll() {
        return tasks;
    }

    @Override
    public boolean deleteByName(String name) {
        for (Task task : tasks) {
            if (task.getName().equals(name)) {
                tasks.remove(task);
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean deleteById(int id) {
      return tasks.removeIf(task -> task.getId() == id);
    }

    public boolean updateTask(Task taskToUpdate, String name, Priority priority) {
        boolean nameExists = tasks.stream().anyMatch(task -> task.getName().equals(name));
        if (nameExists) {
            return false;
        }
        for (Task task : tasks) {
            if (task.equals(taskToUpdate)) {
                task.setName(name);
                task.setPriority(priority);
                return true;
            }
        }
        return false;
    }
}
