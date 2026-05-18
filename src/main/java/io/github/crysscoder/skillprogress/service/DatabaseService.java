package io.github.crysscoder.skillprogress.service;

import io.github.crysscoder.skillprogress.dto.Task;
import io.github.crysscoder.skillprogress.dto.User;
import io.github.crysscoder.skillprogress.repository.Database;
import io.github.crysscoder.skillprogress.repository.skill.DatabaseSkillRepository;
import io.github.crysscoder.skillprogress.repository.task.DatabaseTaskRepository;
import io.github.crysscoder.skillprogress.repository.task.TaskRepository;

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
