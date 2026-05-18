package io.github.crysscoder.skillprogress.repository;


import java.util.concurrent.CompletableFuture;

public interface Repository<T> {
    CompletableFuture<T> add(T entity);

    CompletableFuture<Void> delete(T entity);

    default CompletableFuture<Void> update(T entity) {
        return null;
    }
}
