package dev.cryst.skillprogress.service;

import dev.cryst.skillprogress.dto.Task;
import dev.cryst.skillprogress.dto.User;
import dev.cryst.skillprogress.repository.Database;
import dev.cryst.skillprogress.repository.skill.DatabaseSkillRepository;
import dev.cryst.skillprogress.repository.task.DatabaseTaskRepository;
import dev.cryst.skillprogress.repository.task.TaskRepository;

import java.util.concurrent.CompletableFuture;


public class DatabaseService {
    private final DatabaseSkillRepository skillRepository;
    private final DatabaseTaskRepository taskRepository;

    public DatabaseService(Database database) {
        this.skillRepository = new DatabaseSkillRepository(database);
        this.taskRepository = new DatabaseTaskRepository(database);
    }

    public CompletableFuture<User> add(User user) {
        return skillRepository.add(user);
    }

    public CompletableFuture<Void> remove(User user) {
        return skillRepository.delete(user);
    }

    public CompletableFuture<User> getPlayer(String name) {
        return skillRepository.getPlayer(name);
    }

    public CompletableFuture<Task> add(Task task) {
        return taskRepository.add(task);
    }

    public CompletableFuture<Void> remove(Task task) {
        return taskRepository.delete(task);
    }

    public CompletableFuture<Task> getTask(int id) {
        return taskRepository.get(id);
    }

}
