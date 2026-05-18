package io.github.crysscoder.skillprogress.repository.task;

import io.github.crysscoder.skillprogress.dto.Task;
import io.github.crysscoder.skillprogress.repository.Repository;

import java.util.concurrent.CompletableFuture;

public interface TaskRepository extends Repository<Task> {
    CompletableFuture<Task> get(int id);
}
