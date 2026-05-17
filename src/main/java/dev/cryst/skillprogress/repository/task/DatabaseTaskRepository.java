package dev.cryst.skillprogress.repository.task;

import com.google.gson.Gson;
import dev.cryst.skillprogress.dto.Task;
import dev.cryst.skillprogress.repository.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DatabaseTaskRepository implements TaskRepository {
    private final Database dataSource;

    private final Executor executor = Executors.newFixedThreadPool(3);
    private Gson gson = new Gson();

    public DatabaseTaskRepository(Database dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public CompletableFuture<Task> add(Task entity) {
        return CompletableFuture.supplyAsync(() -> {
            final String jsonTask = gson.toJson(entity);
            final String sql = """
                    INSERT INTO tasks(task_id, data) VALUES(?, ?)
                    ON DUPLICATE KEY UPDATE data = ?;
                    """;

            try(final Connection conn = dataSource.getDs().getConnection();
                final PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, entity.task_id());
                ps.setString(2, jsonTask);

                ps.execute();
                return entity;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }

    @Override
    public CompletableFuture<Void> delete(Task entity) {
       return CompletableFuture.runAsync(() -> {
            final String sql = """
                    DELETE FROM tasks WHERE task_is = ?;
                    """;

            try(final Connection conn = dataSource.getDs().getConnection();
            final PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, entity.task_id());
                ps.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
       }, executor);
    }

    @Override
    public CompletableFuture<Task> get(int id) {
        return CompletableFuture.supplyAsync(() -> {
            final String sql = """
                    SELECT * FROM tasks WHERE skill_id = ?;
                    """;

            try(final Connection conn = dataSource.getDs().getConnection();
            final PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, id);
                try(final ResultSet rs = ps.executeQuery()){
                    if(rs.next()) {
                        return gson.fromJson(rs.getString(2), Task.class);
                    }
                }
                return null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        },executor);
    }
}
