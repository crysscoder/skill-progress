package dev.cryst.skillprogress.repository.task;

import dev.cryst.skillprogress.dto.Task;
import dev.cryst.skillprogress.repository.Repository;

import java.util.concurrent.CompletableFuture;

public interface TaskRepository extends Repository<Task> {
    CompletableFuture<Task> get(int id);
}
